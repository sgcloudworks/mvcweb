package com.codeworks.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class GetJDBCTemplate {
	
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJDBCTemplate(){
		return jdbcTemplate;
	}

}
