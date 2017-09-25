package com.codeworks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codeworks.model.AdminUser;
import com.codeworks.model.Student;

@Service
public interface StudentService {
	
	List<Student> registerStudent(Long adminId,String adminEmailId,Student student);
	
	List<Student> getActiveStudents(Long adminId,String adminEmailId);
	
	List<Student> getStudent(Long adminId,String adminEmailId,Long studentId);
	
	Student getStudentFromEmailId(String emailId);
	
	Student verifyUserExistence(String emailId);
}
