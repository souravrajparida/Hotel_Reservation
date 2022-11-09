package com.cognizant.authorization.repository;

import com.cognizant.authorization.model.UserRegistrationDetails;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest

class UserLoginRepositoryTests {

	@Autowired
	private UserLoginRepository userLoginRepository;

	@Test
	@DisplayName("This method is responsible to test findById() method when user exists in database")
	void testFindUserById_userExists() {
		final String username = "sourav@gmail.com";
		Optional<UserRegistrationDetails> userOptional = userLoginRepository.findById(username);
		assertTrue(userOptional.isPresent());
		assertEquals(username, userOptional.get().getEmailAddress());
	}

	@Test
	@DisplayName("This method is responsible to test findById() method when user does not exists in database")
	void testFindUserById_userDoesNotExists() {
		final String username = "incorrect";
		Optional<UserRegistrationDetails> userOptional = userLoginRepository.findById(username);
		assertTrue(!userOptional.isPresent());
	}
}
