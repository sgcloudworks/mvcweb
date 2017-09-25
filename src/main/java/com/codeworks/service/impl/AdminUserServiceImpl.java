package com.codeworks.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.codeworks.model.AdminUser;
import com.codeworks.model.Student;
import com.codeworks.service.AdminUserService;
import com.codeworks.spring.dao.AdminUserDao;

public class AdminUserServiceImpl implements AdminUserService {
	
	@Autowired
	AdminUserDao adminUserDao;
	
	@Override
	public List<AdminUser> loginValidation(String adminEmailId, String passwrod) {
		// TODO Auto-generated method stub
		return adminUserDao.loginValidation(adminEmailId, passwrod);
	}

	@Override
	public List<AdminUser> getActiveAdminUsers() {
		// TODO Auto-generated method stub
		return adminUserDao.getActiveAdminUsers();
	}

	@Override
	public Student registerStudent(String adminEmailId, Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getStudents(String adminEmailId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
