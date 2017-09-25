package com.codeworks.db.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.springframework.jdbc.core.RowMapper;

import com.codeworks.model.Student;

public class StudentRowMapper implements RowMapper<Student> {
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 * 
	 *  Types.VARCHAR,    // "FIRST_NAME
            Types.VARCHAR,    // "MIDDLE NAME"
            Types.VARCHAR,    // "LAST_NAME"
            Types.VARCHAR,    // "EMAIl_ID"
            Types.VARCHAR,    // "mobile_number"
            Types.VARCHAR,    // "city"
            Types.VARCHAR,    // "state"
            Types.VARCHAR,    // "country"
            Types.INTEGER,	  // zip code	
            Types.BOOLEAN,    // active
            Types.BOOLEAN,    // "certified"
            Types.DATE,    // "target_date"
            Types.DATE,    // "creation_date"
            Types.VARCHAR,    // "CREATEdby"
            Types.VARCHAR     // "invitation code"
	 * 
	 */
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student = null;
		
		if(rs!=null ){
			student = new Student();
			student.setStudentId(rs.getLong("student_id"));
			
			student.setFirstName(rs.getString("first_name"));
			student.setLastName(rs.getString("last_name"));
			student.setEmailId(rs.getString("email_id"));
			student.setMobileNumber(rs.getString("mobile_number"));
			student.setCity(rs.getString("city"));
			student.setState(rs.getString("state"));
			student.setCountry(rs.getString("country"));
			
			student.setZipCode(rs.getInt("zip_code"));
			
			student.setActive( rs.getBoolean("active"));
			student.setCertified( rs.getBoolean("certified"));			
		
			student.setCreationDate(rs.getDate("creation_date"));
			student.setTargetDate(rs.getDate("target_date"));
			
			student.setCreatedBy(rs.getString("created_by"));
			student.setInviteCode(rs.getString("invite_code"));
			
		}
		return student;
	}
	
	

}
