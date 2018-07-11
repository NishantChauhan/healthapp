package com.nishant.healthapp.dao;

import java.util.List;

import com.nishant.healthapp.domain.User;

public interface UserDAO  {
	
	User save(User user);
	
	List<User> findByEmail(String email);
	
	List<User> findByEmailAndPassword(String email, String password);
	
	void update(User user);
	
}
