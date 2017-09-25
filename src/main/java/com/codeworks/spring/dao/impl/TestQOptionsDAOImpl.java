package com.codeworks.spring.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.codeworks.db.rowmapper.TestQOptionsRowMapper;
import com.codeworks.model.TestQuestionOptions;
import com.codeworks.spring.dao.TestQOptionsDAO;

public class TestQOptionsDAOImpl implements TestQOptionsDAO {
	
	private static Map<String,List<TestQuestionOptions>> allTestQs = new HashMap();
	
	/*private static final String FIND_TESTQOPTIONS_BY_ID = "SELECT * FROM PREPATHOME.TEST_QUESTION_OPTIONS WHERE "
			+ "TEST_ID = ? AND IS_ACTIVE='Y'  order by QUESTION_ID";*/
	
	private static final String FIND_TESTQOPTIONS_BY_ID = "SELECT * FROM PREPATHOME.qlots WHERE "
			+ "TEST_ID = ? AND IS_ACTIVE='Y' and LANGUAGE_ID=1 order by QUESTION_ID";
	
	private static final String FIND_TESTQOPTIONS_BY_LANGAUGE_ID = "SELECT * FROM PREPATHOME.qlot WHERE "
			+ "TEST_ID = ? AND IS_ACTIVE='Y' and LANGUAGE_ID=? and QUESTION_ID<21 order by QUESTION_ID";
	

	private static final String LOAD_ALL = "SELECT * FROM PREPATHOME.qlot WHERE IS_ACTIVE='Y' order by TEST_ID,LANGUAGE_ID,QUESTION_ID";
	
	private static final String FIND_TESTQOPTIONS_BY_QUESTION_ID = "SELECT * FROM PREPATHOME.TEST_QUESTION_OPTIONS WHERE "
			+ "TEST_ID = ? AND QUESTION_ID = ? AND IS_ACTIVE='Y'  order by QUESTION_ID";
	
	
	

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Map<Integer,List<TestQuestionOptions>> getAllQOptionsForTestId(int testId) {
		// TODO Auto-generated method stub
		
		Map<Integer,List<TestQuestionOptions>> testIDMap = new HashMap<Integer,List<TestQuestionOptions>>();
		
		List<TestQuestionOptions> testQuestionOptionsList =  jdbcTemplate.query(
				FIND_TESTQOPTIONS_BY_ID, new Object[] { testId }, new TestQOptionsRowMapper());
		
		testIDMap.put(testId, testQuestionOptionsList);
		
		return testIDMap;
	}
	
	@Override
	public Map<Integer,List<TestQuestionOptions>> getAllQOptionsForTestId(int testId,int langId) {
		// TODO Auto-generated method stub
		
		Map<Integer,List<TestQuestionOptions>> testIDMap = new HashMap<Integer,List<TestQuestionOptions>>();
		
		List<TestQuestionOptions> testQuestionOptionsList =  jdbcTemplate.query(
				FIND_TESTQOPTIONS_BY_LANGAUGE_ID, new Object[] { testId, langId}, new TestQOptionsRowMapper());
		
		testIDMap.put(testId, testQuestionOptionsList);
		
		
		return testIDMap;
	}
	
	@Override	
	public TestQuestionOptions getSpecificQuestionForTestId(int testId, int counter) {
		// TODO Auto-generated method stub
		
		TestQuestionOptions testQuestionOptions = null;
		List<TestQuestionOptions> testQuestionOptionsList =  jdbcTemplate.query(
				FIND_TESTQOPTIONS_BY_QUESTION_ID, new Object[] { testId,counter }, new TestQOptionsRowMapper());
		
		if (testQuestionOptionsList.isEmpty()) {
			testQuestionOptions = null;
		} else if (testQuestionOptionsList.size() == 1) { // list contains exactly 1 element
			testQuestionOptions =  testQuestionOptionsList.get(0);
			//testDetail.setCustomer(new Customer());
		}
		
		return testQuestionOptions;
		
	}
	
	
	@Override
	public List<TestQuestionOptions> loadAll() {
		// TODO Auto-generated method stub
		List<TestQuestionOptions> testQuestionOptionsList =  jdbcTemplate.query(
				LOAD_ALL, new TestQOptionsRowMapper());
		
		return testQuestionOptionsList;
	}
}
