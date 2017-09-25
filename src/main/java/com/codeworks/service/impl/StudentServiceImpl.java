package com.codeworks.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.codeworks.model.Student;
import com.codeworks.service.StudentService;
import com.codeworks.spring.dao.StudentDao;

public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDao studentDao;

	@Override
	public List<Student> registerStudent(Long adminId, String adminEmailId, Student student) {
		// TODO Auto-generated method stub
		studentDao.registerStudent(adminId, adminEmailId, student);
		return getActiveStudents(adminId, adminEmailId);
	}

	@Override
	public List<Student> getActiveStudents(Long adminId, String adminEmailId) {
		// TODO Auto-generated method stub

		return studentDao.getActiveStudents(adminId, adminEmailId);
	}

	@Override
	public List<Student> getStudent(Long adminId, String adminEmailId, Long studentId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Student getStudentFromEmailId(String emailId) {
		// TODO Auto-generated method stub
		return studentDao.getStudentFromEmailId(emailId);
	}
	
	
	@Override
	public Student verifyUserExistence(String emailId) {
		// TODO Auto-generated method stub
		return studentDao.verifyUserExistence(emailId);
	}

}
