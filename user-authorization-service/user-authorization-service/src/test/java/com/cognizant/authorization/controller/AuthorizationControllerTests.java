package com.cognizant.authorization.controller;

import com.cognizant.authorization.model.UserRequest;
import com.cognizant.authorization.service.UserServiceImpl;
import com.cognizant.authorization.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest

class AuthorizationControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserServiceImpl userServiceImpl;

	@MockBean
	private JwtUtil jwtUtil;

	@MockBean
	private AuthenticationManager authenticationManager;

	@Value("${userDetails.errorMessage}")
	private String ERROR_MESSAGE;
	
	@Value("${userDetails.badCredentialsMessage}")
	private String BAD_CREDENTIALS_MESSAGE;

	@Value("${userDetails.disabledAccountMessage}")
	private String DISABLED_ACCOUNT_MESSAGE;
	
	@Value("${userDetails.lockedAccountMessage}")
	private String LOCKED_ACCOUNT_MESSAGE;
	
	private static ObjectMapper mapper = new ObjectMapper();
	private static SecurityUser validUser;
	private static SecurityUser invalidUser;

	@BeforeEach
	void generateUserCredentials() {
		// User object
		validUser = new SecurityUser("sourav@gmail.com", "sourav",
				Collections.singletonList(new SimpleGrantedAuthority("ADMIN")));
		invalidUser = new SecurityUser("incorrect", "incorrect",
				Collections.singletonList(new SimpleGrantedAuthority("USER")));
	}


	@Test
	@DisplayName("This method is responsible to test login() method with valid credentials")
	void testLogin_withValidCredentials() throws Exception {

		// Set the user request
		UserRequest user = new UserRequest("sourav@gmail.com", "sourav");

		String token = "";

		// mock certain functionalities to return a valid user and generate the token
		when(authenticationManager.authenticate(ArgumentMatchers.any()))
				.thenReturn(new TestingAuthenticationToken("sourav@gmail.com", "sourav", "ADMIN"));
		when(jwtUtil.generateToken(ArgumentMatchers.any())).thenReturn(token);

		String json = mapper.writeValueAsString(user);

		MvcResult result = mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(json).accept(MediaType.TEXT_PLAIN)).andExpect(status().isOk())
				.andReturn();

		String contentAsString = result.getResponse().getContentAsString();

		assertNotNull(contentAsString);
		// match the token from the response body
		assertEquals(contentAsString, token);

	}

	@Test
	@DisplayName("This method is responsible to test login() method with invalid credentials")
	void testLogin_withInvalidCredentials() throws Exception {

		// Set the user request and role
		UserRequest user = new UserRequest("incorrect", "incorrect");
		
		// mock certain functionalities to return a valid user and generate the token
		when(authenticationManager.authenticate(ArgumentMatchers.any())).thenThrow(new BadCredentialsException(ERROR_MESSAGE));
		
		String json = mapper.writeValueAsString(user);

		mockMvc.perform(post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(json)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.message", Matchers.equalTo(ERROR_MESSAGE)));

	}
	
	// Class to avoid User conflict
	public class SecurityUser extends org.springframework.security.core.userdetails.User {

		private static final long serialVersionUID = -4209816021578748288L;

		public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
			super(username, password, authorities);
		}

	}

}
