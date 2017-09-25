package com.codeworks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codeworks.model.AdminUser;
import com.codeworks.model.Student;

@Service
public interface AdminUserService {

	List<AdminUser> loginValidation(String adminEmailId,String passwrod);
	
	List<AdminUser> getActiveAdminUsers();
	
	Student registerStudent(String adminEmailId,Student student);
	
	List<Student> getStudents(String adminEmailId);
}
