package com.codeworks.spring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.codeworks.db.rowmapper.AdminUserRowMapper;
import com.codeworks.model.AdminUser;
import com.codeworks.model.Student;
import com.codeworks.spring.dao.AdminUserDao;

public class AdminUserDaoImpl implements AdminUserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final String FIND_ADMINUSER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM PREPATHOME.admin_user WHERE "
			+ "admin_email= ? AND active='Y' and admin_pwd=? ";
	
	private static final String FIND_ALL_ACTIVE_ADMIN_USERS = "SELECT * FROM PREPATHOME.admin_user WHERE "
			+ "active='Y' ";

	@Override
	public List<AdminUser> loginValidation(String adminEmailId, String password) {

		List<AdminUser> adminUserList = new ArrayList<AdminUser>();
		adminUserList = jdbcTemplate.query(FIND_ADMINUSER_BY_EMAIL_AND_PASSWORD,
				new Object[] { adminEmailId, password }, new AdminUserRowMapper());

		// TODO Auto-generated method stub
		return adminUserList;
	}

	@Override
	public List<AdminUser> getActiveAdminUsers() {

		List<AdminUser> adminUserList = new ArrayList<AdminUser>();
		adminUserList = jdbcTemplate.query(FIND_ALL_ACTIVE_ADMIN_USERS, new AdminUserRowMapper());

		// TODO Auto-generated method stub
		return adminUserList;
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
