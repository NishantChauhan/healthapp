package com.nishant.healthapp.services;

import com.nishant.healthapp.exceptions.UnmatchingUserCredentialsException;
import com.nishant.healthapp.exceptions.UserNotFoundException;
import com.nishant.healthapp.domain.User;

public interface UserService {
	
	User save(User user);
	
	void update(User user);
	
	User doesUserExist(String email) throws UserNotFoundException;
	
	User getByEmail(String email) throws UserNotFoundException;
	
	User isValidUser(String email, String password) throws UnmatchingUserCredentialsException;
}
