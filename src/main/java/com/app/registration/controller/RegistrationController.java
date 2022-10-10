package com.app.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.registration.model.User1;
import com.app.registration.service.RegistrationService;

@RestController
public class RegistrationController {
	
	
	@Autowired
	private RegistrationService service;
	
	@PostMapping("/registeruser")
	@CrossOrigin(origins = "http://localhost:4200")
	public User1 registerUser(@RequestBody User1 user1) throws Exception{
		String tempEmailId=user1.getEmailId();
		if(tempEmailId != null && !"".equals(tempEmailId)){
			User1 userobj = service.fetchUserByEmailId(tempEmailId);
			if(userobj !=null){
				throw new Exception("user with "+tempEmailId+"is already exit");
			}
		}
		User1 userObj=null;
		userObj=service.saveUser(user1);
		return userObj;
		
	}
	
	
	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public User1 loginUser(@RequestBody User1 user1) throws Exception{
		String tempEmailId=user1.getEmailId();
		String tempPass=user1.getPassword();
		User1 userObj = null;
		if(tempEmailId != null && tempPass != null){
			userObj=service.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);
		}
		
		if(userObj == null){
			throw new Exception("Bad credentials");
		}
		return userObj;
	}

}
