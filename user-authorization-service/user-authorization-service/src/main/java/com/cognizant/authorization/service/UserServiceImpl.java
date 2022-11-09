package com.cognizant.authorization.service;

import com.cognizant.authorization.exception.InvalidCredentialsException;
import com.cognizant.authorization.model.UserRegistrationDetails;
import com.cognizant.authorization.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service

public class UserServiceImpl implements UserDetailsService {

	@Value("${userDetails.errorMessage}")
	private String USER_DOES_NOT_EXIST_MESSAGE;

	@Autowired
	private UserLoginRepository userLoginRepository;

	public UserRegistrationDetails saveUser(UserRegistrationDetails user){
		return userLoginRepository.save(user);
	}

	public UserRegistrationDetails fetchUserByEmailId(String email){
		return userLoginRepository.findByEmailAddress(email);
	}

	/*public Optional<UserLoginDetails> findByUsername(String username) {
		return userLoginRepository.findById(username);
	}
*/

	@Override
	public UserDetails loadUserByUsername(String email) {
		Optional<UserRegistrationDetails> userOptional = Optional.ofNullable(fetchUserByEmailId(email));
		if (!userOptional.isPresent()) {
			throw new InvalidCredentialsException(USER_DOES_NOT_EXIST_MESSAGE);
		} else {
			UserRegistrationDetails user = userOptional.get();
			return new org.springframework.security.core.userdetails.User(email, user.getPassword(),
					Collections.singletonList(new SimpleGrantedAuthority("ADMIN")));
		}
	}

}
