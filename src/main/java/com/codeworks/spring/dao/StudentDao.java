package com.codeworks.spring.dao;

import java.util.List;

import com.codeworks.model.AdminUser;
import com.codeworks.model.Student;

public interface StudentDao {
	
	Student registerStudent(Long adminId,String adminEmailId,Student student);
	
	List<Student> getActiveStudents(Long adminId,String adminEmailId);
	
	List<Student> getStudent(Long adminId,String adminEmailId,Long studentId);
	
	Student getStudentFromEmailId(String emailId);
	
	Student verifyUserExistence(String emailId);
	
}
