package com.cognizant.authorization;

import com.cognizant.authorization.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthorizationServiceApplicationTests {

	@Autowired
	private UserServiceImpl userServiceImpl;
}

