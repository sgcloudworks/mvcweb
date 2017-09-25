package com.codeworks.spring.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.codeworks.db.rowmapper.StudentCustomRowMapper;
import com.codeworks.db.rowmapper.StudentRowMapper;
import com.codeworks.model.Student;
import com.codeworks.spring.dao.StudentDao;

public class StudentDaoImpl implements StudentDao {
	
	private static final String LIST_STUDENTS_BY_CREATOR = "SELECT * FROM prepathome.student where upper(created_by) = upper(?)  AND active='Y' ";
	
	private static final String REGISTER_STUDENT = "INSERT INTO prepathome.student( "
			+ "	first_name, middle_name, last_name, email_id, mobile_number, city, state, country, zip_code, active, certified, target_date, creation_date,  created_by, invite_code) "
			+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ?, ?);";;

	private final static String GET_STUDENT_DATA_FROM_EMAIL_ID = "SELECT * FROM prepathome.student where active='Y' "
			+ "	and upper(email_id) = upper(?)";
	
	private final static String USER_EXISTS = "SELECT * "
													+ "FROM prepathome.student stu "
													+ "WHERE upper(stu.email_id) = upper(?) "
													+ "  AND NOT EXISTS "
													+ "    (SELECT us.student_id "
													+ "     FROM prepathome.user us "
													+ "     WHERE stu.student_id = us.student_id)";
	
	private final static String STUD_USER_EXISTS = "SELECT stud.*, "
														+ "       CASE "
														+ "           WHEN EXISTS "
														+ "                  ( SELECT * "
														+ "                   FROM prepathome.student "
														+ "                   WHERE upper(email_id) =upper(?) ) THEN CAST(TRUE AS boolean) "
														+ "           ELSE CAST(FALSE AS boolean) "
														+ "       END AS stud_exists, "
														+ "       CASE "
														+ "           WHEN EXISTS "
														+ "                  ( SELECT * "
														+ "                   FROM prepathome.user "
														+ "                   WHERE upper(email_id) =upper(?)) THEN CAST(TRUE AS boolean) "
														+ "           ELSE CAST(FALSE AS boolean) "
														+ "       END AS user_exists "
														+ "FROM prepathome.student stud "
														+ "WHERE upper(email_id) =upper(?)";

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Student getStudentFromEmailId(String emailId) {

		Student student = new Student();

		List<Student> studentList = new ArrayList<Student>();
		studentList = jdbcTemplate.query(GET_STUDENT_DATA_FROM_EMAIL_ID, new Object[] { emailId },
				new StudentRowMapper());
		if (!studentList.isEmpty()) {
			student = studentList.get(0);
		}
		return student;
	}
	
	@Override
	public Student verifyUserExistence(String emailId) {

		Student student = new Student();

		List<Student> studentList = new ArrayList<Student>();
		studentList = jdbcTemplate.query(STUD_USER_EXISTS, new Object[] { emailId,emailId,emailId },
				new StudentCustomRowMapper());
		if (!studentList.isEmpty()) {
			student = studentList.get(0);
		}
		return student;
	}


	@Override
	public Student registerStudent(Long adminId, String adminEmailId, Student student) {
		
		/*if (object.getCmprlId() == 0L) {
            object.setCmprlId(getSequencer().getNextValue("SEQ_CMPRL_ID"));
        }*/
        int rows = jdbcTemplate.update(REGISTER_STUDENT, getParametersForFullInsert(student),
                getParameterTypesForFullInsert());
        /*if (rows != 1) {
            throw new JdbcUpdateAffectedIncorrectNumberOfRowsException(sql, 1, rows);
        }*/
        
        //return null;
		// TODO Auto-generated method stub
		
		//jdbcTemplate.update(LIST_STUDENTS_BY_CREATOR,new Object[]{st.getSno(),st.getSname(),st.getAge()});
		return null;
	}

	@Override
	public List<Student> getActiveStudents(Long adminId, String adminEmailId) {
		
		List<Student> studentList = new ArrayList<Student>();
		studentList = jdbcTemplate.query(LIST_STUDENTS_BY_CREATOR, new Object[] { adminEmailId}, new StudentRowMapper());

		// TODO Auto-generated method stub
		return studentList;
	}

	@Override
	public List<Student> getStudent(Long adminId,String adminEmailId,Long studentId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * INSERT INTO prepathome.student
            (first_name,
             middle_name,
             last_name,
             email_id,
             mobile_number,
             city,
             state,
             country,
             zip_code,
             active,
             certified,
             target_date,
             creation_date,
             created_by,
             invite_code)
VALUES      (?,
             ?,
             ?,
             ?,
             ?,
             ?,
             ?,
             ?,
             ?,
             ?,
             ?,
             ?,
             ?,
             ?,
             ?,
             ?); 
     * 
     */
	
	
    protected Object[] getParametersForFullInsert(Student obj) {
    	
        Object[] params = new Object[15];
        params[0] = obj.getFirstName();
        params[1] = obj.getMiddleName();
        params[2] = obj.getLastName();
        params[3] = obj.getEmailId();
        params[4] = obj.getMobileNumber();
        params[5] = obj.getCity();
        params[6] = obj.getState();
        params[7] = obj.getCountry();
        params[8] = obj.getZipCode();
        params[9] = obj.getActive();
        params[10] = obj.getCertified();
        params[11] = obj.getTargetDate();
        params[12] = obj.getCreationDate();
        params[13] = obj.getCreatedBy();
        params[14] = obj.getInviteCode();
        return params;
    }
    /*
     * 
    first_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    middle_name character varying(50) COLLATE pg_catalog."default",
    last_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    email_id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    mobile_number character varying(30) COLLATE pg_catalog."default" NOT NULL,
    city character varying(100) COLLATE pg_catalog."default" NOT NULL,
    state character varying(100) COLLATE pg_catalog."default" NOT NULL,
    country character varying(100) COLLATE pg_catalog."default" NOT NULL,
    zip_code integer NOT NULL,
    active boolean NOT NULL,
    certified boolean NOT NULL,
    target_date date,
    creation_date date NOT NULL,
    modified_date date,
    created_by character varying(100) COLLATE pg_catalog."default" NOT NULL,
    invite_code character varying(30) COLLATE pg_catalog."default" NOT NULL,
     */
    
    protected int[] getParameterTypesForFullInsert() {
        return new int[] {
            Types.VARCHAR,    // "FIRST_NAME
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
        };
    }

	

	

}
