package com.codeworks.db.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.codeworks.model.AdminUser;

public class AdminUserRowMapper implements RowMapper<AdminUser> {
	
	public AdminUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		AdminUser adminUser = null;
		
		if(rs!=null ){
			adminUser = new AdminUser();
			adminUser.setAdminEmail(rs.getString("admin_email"));
			adminUser.setAdminId(rs.getLong("admin_id"));
			//adminUser.setAdminPwd(rs.getString("admin_pwd"));
			adminUser.setRoleId(rs.getInt("role_id"));
			adminUser.setActive(rs.getBoolean("active"));
			adminUser.setFirstName(rs.getString("first_name"));
			adminUser.setLastName(rs.getString("last_name"));
		}
		return adminUser;
	}

}
