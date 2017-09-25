package com.codeworks.db.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;

public class PwdCryptoHelper {
		
	
	public static String APP_SALT = "prep@#ome";
	
	private static String CRYPTO_QUERY = "select prepathome.crypt(?, prepathome.gen_salt('bf', 8))";
	
	private static String MATCH_CRYPTO = "select (? = prepathome.crypt(?,?) ) as matched" ;
			
	
	//Generate Pwd Salt using postgres
		/*Step 1: Generate Salt
		 * 	select crypt('userpwdtext', gen_salt('bf')); is resulting in 
		 * 			$2a$08$J15I/N6P64XwvJyejZWsPOvL1S/lhN.FxsljY/BGZ7kT.uy8rhBAO  -- save it in salt column
		 * Step 2: supply this salt to another round crypto and put it in password column
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 *  1. SAlt Generation - select prepathome.crypt('userpwdtext', prepathome.gen_salt('bf', 8));

			2. pwd crypto generation - select prepathome.crypt('$2a$08$ZQJQD2eZn5DZGeu5zEbYAuyVU.gA8LI3Pco/0qrqnQsxgIaBb8lVi', prepathome.gen_salt('bf', 8));
			
			3. Comparison - select (
			    '$2a$08$8X7O87PLEUJkowN2f4dvkeTfVL2NvJRsLUf0hfwJGo.HqF7PExTFm' =
			    prepathome.crypt(
			      '$2a$08$ZQJQD2eZn5DZGeu5zEbYAuyVU.gA8LI3Pco/0qrqnQsxgIaBb8lVi',
			      '$2a$08$8X7O87PLEUJkowN2f4dvkeTfVL2NvJRsLUf0hfwJGo.HqF7PExTFm'
			    )
			  ) as matched;
		 * 
		 * 
		 * 
		 * 
		 */
	          
	public String getCryptoString(String stringTobeCrypted,JdbcTemplate jdbcTemplate) {
		
		SingleColumnRowMapper<String> rowMapper = new SingleColumnRowMapper<String>(String.class); 
		List<String> passwordCrypt = (ArrayList<String>) jdbcTemplate.query(CRYPTO_QUERY, new Object[]{stringTobeCrypted},rowMapper);
		return passwordCrypt.get(0);
		 
	}
	
	public boolean matchSaltAndPwd(String suppliedPassword,String salt,JdbcTemplate jdbcTemplate){
		
		SingleColumnRowMapper<Boolean> rowMapper = new SingleColumnRowMapper<Boolean>(Boolean.class);
		
		List<Boolean> passwordCrypt = (ArrayList<Boolean>) jdbcTemplate.query(MATCH_CRYPTO, new Object[]{salt,suppliedPassword,salt,},rowMapper);
		System.out.println(" passwords match "+passwordCrypt.get(0));
		return passwordCrypt.get(0);
	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String someText = new PwdCryptoHelper().getCryptoString("somePwd"+APP_SALT);
		
		System.out.println(" initial salt "+someText);
		
		someText = new PwdCryptoHelper().getCryptoString(someText);
		
		System.out.println(" another salt "+someText);


	}*/

}
