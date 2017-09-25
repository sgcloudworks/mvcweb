package com.codeworks.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.codeworks.model.TestQuestionOptions;
import com.codeworks.service.TestQOptionsService;
import com.codeworks.spring.dao.TestQOptionsDAO;

public class TestQOptionsServiceImpl implements TestQOptionsService{
	
	private static Map<String,List<TestQuestionOptions>> testQsCacheMap = new HashMap();
	
	@Autowired
	TestQOptionsDAO testQOptionsDAO;
	
	

	@Override
	public TestQuestionOptions getSpecificQuestionForTestId(int testId, int counter) {
		// TODO Auto-generated method stub
		return testQOptionsDAO.getSpecificQuestionForTestId(testId, counter);
	}

	@Override
	public Map<Integer,List<TestQuestionOptions>> getAllQOptionsForTestId(int testId) {
		// TODO Auto-generated method stub
		
		return testQOptionsDAO.getAllQOptionsForTestId(testId);
	}
	
	@Override
	public Map<Integer,List<TestQuestionOptions>> getAllQOptionsForTestId(int testId,int langId) {
		return testQOptionsDAO.getAllQOptionsForTestId(testId,langId);
	}
	
	@Override
	public Map<String,List<TestQuestionOptions>> populateQNOptionsForATestAndLanguage(int testId,int langId) {
		
		
		//return populateQuestionsMap(testId, langId);
		String testIdLangId = ""+testId+""+langId;
		
		if (testQsCacheMap.get(testIdLangId) == null) {
			
			List<TestQuestionOptions> allTestsDataForAllLangs = testQOptionsDAO.loadAll();
			
			if(allTestsDataForAllLangs!=null && allTestsDataForAllLangs.size()>0){
				populateTestQsOptionsForAllLanguages(allTestsDataForAllLangs);
			}
			
			//testQsCacheMap.put(testIdLangId, (testQOptionsDAO.getAllQOptionsForTestId(testId, langId)).get(testId));
		}
		
		return testQsCacheMap;
	}
	
	private void populateTestQsOptionsForAllLanguages(List<TestQuestionOptions> wholeTestData){
		
		for(TestQuestionOptions tq : wholeTestData){
			int testId = tq.getTestId();
			int langId = tq.getLanguageId();
			String testIdLangId = ""+testId+""+langId;
			List<TestQuestionOptions> temp = null;
			if (testQsCacheMap.get(testIdLangId) == null) {				
				temp = new ArrayList();
			} else {
				temp = testQsCacheMap.get(testIdLangId);
			}
			temp.add(tq);
			testQsCacheMap.put(testIdLangId,temp);
			 
		}
	}

	/**
	 * @param tq
	 * @param testIdLangId
	 */
	private void addToMap(TestQuestionOptions tq, String testIdLangId) {
		if (testQsCacheMap.get(testIdLangId) == null) {
			List<TestQuestionOptions> temp = new ArrayList();
			temp.add(tq);
			testQsCacheMap.put(testIdLangId,temp);
		} else {
			
			List<TestQuestionOptions> temp = testQsCacheMap.get(testIdLangId);
			temp.add(tq);
			testQsCacheMap.put(testIdLangId, temp);
		}
	}

	
	

}
