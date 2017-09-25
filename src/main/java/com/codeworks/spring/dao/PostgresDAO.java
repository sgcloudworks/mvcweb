package com.codeworks.spring.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

//@Component
public class PostgresDAO {
	
	private DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
		
}
