package com.codeworks.spring.dao;

import java.util.List;

import com.codeworks.model.AdminUser;
import com.codeworks.model.Student;

public interface AdminUserDao {
	
	List<AdminUser> loginValidation(String adminEmailId,String passwrod);
	
	List<AdminUser> getActiveAdminUsers();
	
	Student registerStudent(String adminEmailId,Student student);
	
	List<Student> getStudents(String adminEmailId);
	
}
