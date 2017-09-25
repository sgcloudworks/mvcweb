package com.codeworks.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.codeworks.db.helper.PwdCryptoHelper;
import com.codeworks.helper.DateHelper;
import com.codeworks.model.User;
import com.codeworks.service.UserService;
import com.codeworks.spring.dao.UserDao;

public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	public int resetPwd(User user) {
		return userDao.resetPwd(user);
	}
	
	public User loadUser(String emailId){
		return userDao.loadUser(emailId);
	}
	
	@Override
	public int registerUser(User user) {
		// TODO Auto-generated method stub
		
		user.setCreatedBy(user.getFirstName()+" "+user.getLastName());
		user.setCreationDate(DateHelper.getCurrentDateInUTC());
		user.setAccountExpiresOn(DateHelper.createTargetDate(user.getCreationDate(), 90));
		user.setTargetDate(DateHelper.createTargetDate(user.getCreationDate(), 90));
		user.setPwdExpiryDate(DateHelper.createTargetDate(user.getCreationDate(), 45));
		
		user.setRoleId(2);
		user.setActive(Boolean.TRUE);		
		
		int rowInserted = userDao.registerUser(user);
		
		
		return rowInserted;
	}

	@Override
	public User verifyLogin(User user) {
		// TODO Auto-generated method stub
		User newUser = userDao.verifyLogin(user);
		return newUser;
	}
	
	
}
