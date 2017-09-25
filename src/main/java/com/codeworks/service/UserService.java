package com.codeworks.service;

import org.springframework.stereotype.Service;

import com.codeworks.model.User;

@Service
public interface UserService {
	
	int registerUser(User user);
	
	User verifyLogin(User user);
	
	User loadUser(String emailId);
	
	int resetPwd(User user);

}
