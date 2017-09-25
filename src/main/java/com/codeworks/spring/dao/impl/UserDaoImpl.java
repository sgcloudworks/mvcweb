package com.codeworks.spring.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.codeworks.db.helper.PwdCryptoHelper;
import com.codeworks.db.rowmapper.UserRowMapper;
import com.codeworks.helper.DateHelper;
import com.codeworks.model.User;
import com.codeworks.spring.dao.UserDao;

public class UserDaoImpl implements UserDao{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private final static String USER_INSERT_QUERY =  "INSERT INTO prepathome.user( "
															+ "		 email_id, "
															+ "		 salt, "
															+ "		 password, "
															+ "		 active, "
															+ "		 target_date, "
															+ "		 creation_date, "
															+ "		 created_by, "
															+ "		 pwd_expiry_date, "
															+ "		 account_expires_on, "
															+ "		 student_id, "
															+ "		 role_id) "
															+ "			VALUES ( "
															+ "		 ?, "
															+ "		 ?, "
															+ "		 ?, "
															+ "		 ?, "
															+ "		 ?, "
															+ "		 ?, "
															+ "		 ?, "
															+ "		 ?, "
															+ "		 ?, "
															+ "		 ?, "
															+ "		 ? "
															+ "		 )";
	
	
	private final static String GET_USER_DATA = "SELECT * from prepathome.user where upper(email_id) = upper(?)";
	
	private final static String UPDATE_USER_PWD = "UPDATE prepathome.user set password = ? , modified_by = ? , modified_date = ? where upper(email_id) = upper(?)";
	

	@Override
	public int registerUser(User user) {
		
		User newUser = new User();
		
		String salt = new PwdCryptoHelper().getCryptoString(user.getPassword()+PwdCryptoHelper.APP_SALT,jdbcTemplate);
		
		user.setSalt(salt);
		
		//salt = new PwdCryptoHelper().getCryptoString(salt);
		
		user.setPassword(salt);
		
		/*GeneratedKeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
		    @Override
		    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		        PreparedStatement statement = con.prepareStatement
		        		(USER_INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
		        statement.
		        statement.setString(1, "SomeName");
		        statement.setString(2, "SomeValue");
		        return statement;
		    }
		}, holder);

		long primaryKey = holder.getKey().longValue();*/
		
		int rows = jdbcTemplate.update(USER_INSERT_QUERY, 
										getParametersForFullInsert(user),
                						getParameterTypesForFullInsert());
		
		
		return rows;
	}
	
	
	@Override
	public int resetPwd(User user) {

		String pwdSalt = new PwdCryptoHelper().getCryptoString(user.getPassword() + PwdCryptoHelper.APP_SALT,
				jdbcTemplate);

		// int rowsUpdated = jdbcTemplate.update(UPDATE_USER_PWD,new Object[] {
		// user.getEmailId(),pwdSalt});

		return jdbcTemplate.update(UPDATE_USER_PWD, new Object[] {  pwdSalt,user.getModifiedBy(), user.getModifiedDate(),user.getEmailId() });
	}
	
	@Override
	public User loadUser(String emailId) {

		List<User> userList = getUserListFromDB(emailId);
		User userFromDB = new User();

		if (!userList.isEmpty()) {
			userFromDB = userList.get(0);
			
		} else {
			userFromDB.setAdminMessage("User Account not found for the given email id. Please contact your instructor for help");
		}
		return userFromDB;
	}

	private List<User> getUserListFromDB(String emailId) {
		List<User> userList = new ArrayList<User>();
		
		userList = jdbcTemplate.query(GET_USER_DATA,
				new Object[] { emailId}, new UserRowMapper());
		
		return userList;
	}

	@Override
	public User verifyLogin(User user) {
		
		User userFromDB = new User();
		boolean suppliedPasswordsMatch = false;

		List<User> userList = getUserListFromDB(user.getEmailId());

		if (!userList.isEmpty()) {
			userFromDB = userList.get(0);
			suppliedPasswordsMatch = new PwdCryptoHelper().matchSaltAndPwd(
					user.getPassword() + PwdCryptoHelper.APP_SALT, userFromDB.getPassword(), jdbcTemplate);
			System.out.println(" Hello passwrods matched " + suppliedPasswordsMatch);
			if (!suppliedPasswordsMatch) {
				userFromDB = new User();
				userFromDB.setAdminMessage(
						"Invalid password. Please enter the correct password or try Forgot Password option");
			} else if (suppliedPasswordsMatch && (!userFromDB.getActive()
					|| !(userFromDB.getTargetDate().after(DateHelper.getCurrentDateInUTC())))) {
				userFromDB = new User();
				userFromDB.setAdminMessage("User Account is inactive. Please contact your instructor for help");
			}
		} else {
			userFromDB.setAdminMessage(
					"User Account not found for the given email id. Please contact your instructor for help");
		}
		return userFromDB;
	}
	
	
	/*
	 * 
	 * 	INSERT INTO prepathome."user"(
		 email_id,
		 salt,
		 password,
		 active,
		 target_date,
		 creation_date,
		 created_by,
		 pwd_expiry_date,
		 account_expires_on,
		 student_id,
		 role_id)
			VALUES (
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
		 ?
		 );
	 * 
	 */
	
	protected Object[] getParametersForFullInsert(User obj) {
    	
        Object[] params = new Object[11];
        params[0] = obj.getEmailId();
        params[1] = obj.getSalt();
        params[2] = obj.getPassword();
        params[3] = obj.getActive();
        params[4] = obj.getTargetDate();
        params[5] = obj.getCreationDate();
        params[6] = obj.getCreatedBy();
        params[7] = obj.getPwdExpiryDate();
        params[8] = obj.getAccountExpiresOn();
        params[9] = obj.getStudentId();
        params[10] = obj.getRoleId();
        return params;
    }
	
	/**
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
	
	protected int[] getParameterTypesForFullInsert() {
        return new int[] {
            Types.VARCHAR,    // "EMAIL_ID
            Types.VARCHAR,    // "SALT"
            Types.VARCHAR,    // "PASSWORD"
            Types.BOOLEAN,    // "ACTIVE"
            Types.DATE,    // "TARGET_DATE"
            Types.DATE,    // "CREATION_DATE"
            Types.VARCHAR,    // "CREATED BY"
            Types.DATE,    // "PASSWORD_EXPIRY_DATE"
            Types.DATE,	  // ACCOUNT_EXPIRES_ON	
            Types.INTEGER,    // STUDENT_ID
            Types.INTEGER   //ROLE_ID
        };
    }
	
	                 
	
	
}
