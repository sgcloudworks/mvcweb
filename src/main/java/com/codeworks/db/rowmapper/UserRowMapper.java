package com.codeworks.db.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.codeworks.model.AdminUser;
import com.codeworks.model.User;

public class UserRowMapper implements RowMapper<User> {
	
	
	/**
	 * /**
	 * user_id integer NOT NULL DEFAULT nextval('prepathome.user_seq'::regclass),
    email_id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    salt text COLLATE pg_catalog."default" NOT NULL,
    password text COLLATE pg_catalog."default" NOT NULL,
    active boolean NOT NULL,
    target_date date,
    creation_date date NOT NULL,
    created_by character varying(100) COLLATE pg_catalog."default" NOT NULL,
    modified_date date,
    modified_by character varying(100) COLLATE pg_catalog."default",
    pwd_expiry_date date,
    account_expires_on date,
    student_id integer NOT NULL,
    alt_email_id character varying(100) COLLATE pg_catalog."default",
    role_id integer NOT NULL,
	 */
	public  User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = null;
		
		if(rs!=null ){
			
			user = new User();
			user.setUserId(rs.getInt("user_id"));
			user.setEmailId(rs.getString("email_id"));
			user.setPassword(rs.getString("password"));
			user.setActive(rs.getBoolean("active"));
			user.setTargetDate(rs.getDate("target_date"));
			user.setCreationDate(rs.getDate("creation_date"));
			user.setCreatedBy(rs.getString("created_by"));
			user.setPwdExpiryDate(rs.getDate("pwd_expiry_date"));
			user.setAccountExpiresOn(rs.getDate("account_expires_on"));
			user.setStudentId(rs.getLong("student_id"));
			user.setRoleId(rs.getInt("role_id"));
			
			/*
			
			user.setCreatedBy(rs.getString(""));
			//adminUser.setAdminPwd(rs.getString("admin_pwd"));
			user.setRoleId(rs.getInt("role_id"));
			user.setActive(rs.getBoolean("active"));
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));*/
		}
		return user;
	}

}
