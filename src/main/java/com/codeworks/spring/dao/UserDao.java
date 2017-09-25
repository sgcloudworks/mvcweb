package com.codeworks.spring.dao;

import com.codeworks.model.User;

public interface UserDao {
	
	int registerUser(User user);
	
	User verifyLogin(User user);
	
	User loadUser(String emailId);
	
	int resetPwd(User user);
	
}
