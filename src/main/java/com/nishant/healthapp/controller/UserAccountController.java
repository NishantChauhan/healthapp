package com.nishant.healthapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nishant.healthapp.domain.User;
import com.nishant.healthapp.exceptions.UnmatchingUserCredentialsException;
import com.nishant.healthapp.helpers.ExecutionStatus;
import com.nishant.healthapp.services.UserService;

@RestController
@RequestMapping("/account/*")
public class UserAccountController {

	final static Logger logger = 
			   LoggerFactory.getLogger(UserAccountController.class);

	private UserService userService;

	@Autowired
	public UserAccountController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value="/login/process", produces="application/json")
 public @ResponseBody ExecutionStatus processLogin(ModelMap model, 
   @RequestBody User reqUser) {
 
   User user = null;
   try {
     user = userService.isValidUser(reqUser.getEmail(),  
       reqUser.getPassword());
   } catch (UnmatchingUserCredentialsException ex) {
     logger.debug(ex.getMessage(), ex);
   }
   if(user == null) {
     return new ExecutionStatus("USER_LOGIN_UNSUCCESSFUL", "Username or password is incorrect. Please try again!");
   }
   return new ExecutionStatus("USER_LOGIN_SUCCESSFUL", "Login Successful!");
 }

}