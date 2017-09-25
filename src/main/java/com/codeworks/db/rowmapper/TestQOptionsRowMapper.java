package com.codeworks.db.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.codeworks.model.TestQuestionOptions;


/*
 * 
 * option_id, option_a, option_b, option_c, option_d, correct_answer, 
 * explanation_steps, 
 * reference_link, test_id, question_id, question, is_active
 */
public class TestQOptionsRowMapper implements RowMapper<TestQuestionOptions> {
	
	/*public TestQuestionOptions mapRow(ResultSet rs, int rowNum) throws SQLException {
		TestQuestionOptions testQuestionOptions = null;
		
		if(rs!=null ){
			testQuestionOptions = new TestQuestionOptions();
			testQuestionOptions.setTestId(rs.getInt("TEST_ID"));
			testQuestionOptions.setQuestionId(rs.getInt("QUESTION_ID"));
			testQuestionOptions.setOptionId(rs.getInt("OPTION_ID"));
			testQuestionOptions.setQuestion(new StringBuffer(rs.getString("QUESTION")));
			StringBuffer testQuestionOptionsArray[] = testQuestionOptions.getOptions();
			
			testQuestionOptionsArray[0] = new StringBuffer(rs.getString("OPTION_A"));
			testQuestionOptionsArray[1] = new StringBuffer(rs.getString("OPTION_B"));
			testQuestionOptionsArray[2] = new StringBuffer(rs.getString("OPTION_C"));
			testQuestionOptionsArray[3] = new StringBuffer(rs.getString("OPTION_D"));
			
			
			testQuestionOptions.setOptions(testQuestionOptionsArray);
			
			testQuestionOptions.setCorrectAnswer(rs.getString("CORRECT_ANSWER"));
			testQuestionOptions.setExplanationSteps(new StringBuffer(rs.getString("EXPLANATION_STEPS")));
			testQuestionOptions.setReferenceLink(new StringBuffer(rs.getString("REFERENCE_LINK")));
			testQuestionOptions.setIsActive(rs.getString("IS_ACTIVE"));
			
			
			testDetail.setTestName(rs.getString("TEST_NAME"));
			testDetail.setTestDescription(rs.getString("TEST_DESCRIPTION"));
			testDetail.setProffession(rs.getString("PROFFESSION"));		
			testDetail.setQuestions(rs.getInt("QUESTIONS"));
			testDetail.setIsActive(rs.getString("IS_ACTIVE"));
		}
		
		
		
		return testQuestionOptions;
	}*/
	
	public TestQuestionOptions mapRow(ResultSet rs, int rowNum) throws SQLException {
		TestQuestionOptions testQuestionOptions = null;
		
		if(rs!=null ){
			testQuestionOptions = new TestQuestionOptions();
			testQuestionOptions.setLanguageId(rs.getInt("LANGUAGE_ID"));
			testQuestionOptions.setTestId(rs.getInt("TEST_ID"));
			testQuestionOptions.setQuestionId(rs.getInt("QUESTION_ID"));
			//testQuestionOptions.setOptionId(rs.getInt("OPTION_ID"));
			testQuestionOptions.setQuestion(new StringBuffer(rs.getString("QUESTION")));
			StringBuffer testQuestionOptionsArray[] = testQuestionOptions.getOptions();
			
			testQuestionOptionsArray[0] = new StringBuffer(rs.getString("OPTION_A"));
			testQuestionOptionsArray[1] = new StringBuffer(rs.getString("OPTION_B"));
			testQuestionOptionsArray[2] = new StringBuffer(rs.getString("OPTION_C"));
			testQuestionOptionsArray[3] = new StringBuffer(rs.getString("OPTION_D"));
			
			
			testQuestionOptions.setOptions(testQuestionOptionsArray);
			
			testQuestionOptions.setCorrectAnswer(rs.getString("CORRECT_OPTION"));
			testQuestionOptions.setExplanationSteps(new StringBuffer(rs.getString("EXPLANATION_STEPS")));
			testQuestionOptions.setReferenceLink(new StringBuffer(rs.getString("REFERENCE_LINKs")));
			testQuestionOptions.setIsActive(rs.getString("IS_ACTIVE"));
			
			
			/*testDetail.setTestName(rs.getString("TEST_NAME"));
			testDetail.setTestDescription(rs.getString("TEST_DESCRIPTION"));
			testDetail.setProffession(rs.getString("PROFFESSION"));		
			testDetail.setQuestions(rs.getInt("QUESTIONS"));
			testDetail.setIsActive(rs.getString("IS_ACTIVE"));*/
		}
		
		
		
		return testQuestionOptions;
	}
	
	

}
