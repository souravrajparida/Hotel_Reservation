package com.cognizant.authorization.controller;

import com.cognizant.authorization.exception.InvalidCredentialsException;
import com.cognizant.authorization.model.UserRegistrationDetails;
import com.cognizant.authorization.model.UserRequest;
import com.cognizant.authorization.service.UserServiceImpl;
import com.cognizant.authorization.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Slf4j
@CrossOrigin
public class AuthorizationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userService;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Value("${userDetails.badCredentialsMessage}")
	private String BAD_CREDENTIALS_MESSAGE;

	@Value("${userDetails.disabledAccountMessage}")
	private String DISABLED_ACCOUNT_MESSAGE;
	
	@Value("${userDetails.lockedAccountMessage}")
	private String LOCKED_ACCOUNT_MESSAGE;

	@PostMapping("/registerUser")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDetails user) throws Exception{
		String tempEmailId = user.getEmailAddress();
		if(tempEmailId != null && !"".equals(tempEmailId)){
			UserRegistrationDetails userobj = userServiceImpl.fetchUserByEmailId(tempEmailId);
			if(userobj !=null){
				throw new Exception("User with "+tempEmailId+" already exists");
			}
		}
		String message =  "Registration successful, please Login!";
		userServiceImpl.saveUser(user);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200")

	public ResponseEntity<String> login(@RequestBody @Valid UserRequest userRequest) {
		try {
			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
			if (authenticate.isAuthenticated()) {
				log.info("User credentials authenticated");
			}
		} catch (BadCredentialsException e) {
			throw new InvalidCredentialsException(BAD_CREDENTIALS_MESSAGE);
		} catch (DisabledException e) {
			throw new InvalidCredentialsException(DISABLED_ACCOUNT_MESSAGE);
		} catch (LockedException e) {
			throw new InvalidCredentialsException(LOCKED_ACCOUNT_MESSAGE);
		}

		String token = jwtUtil.generateToken(userRequest.getUsername());
		log.info("Jwt Token Generated");
		return new ResponseEntity<String>(token, HttpStatus.OK);
	}


	@GetMapping("/validate")
	public ResponseEntity<Boolean> validateAdmin(@RequestHeader(name = "Authorization") String token) {

		jwtUtil.isTokenExpiredOrInvalidFormat(token);


		UserDetails user = userService.loadUserByUsername(jwtUtil.getUsernameFromToken(token));
		if (user.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
			log.info("Jwt Token Validated");
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		log.info("Jwt Token Validation Failed");
		return new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);

	}


	@GetMapping(value = "/statusCheck")
	public String statusCheck() {
		return "OK";
	}
}
