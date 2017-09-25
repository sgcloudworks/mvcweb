package com.codeworks.spring.dao;

import java.util.List;
import java.util.Map;

import com.codeworks.model.TestQuestionOptions;

public interface TestQOptionsDAO {
	
	Map<Integer,List<TestQuestionOptions>> getAllQOptionsForTestId(int testId);
	Map<Integer,List<TestQuestionOptions>> getAllQOptionsForTestId(int testId,int langId);
	TestQuestionOptions getSpecificQuestionForTestId(int testId,int counter);
	List<TestQuestionOptions> loadAll();

}
