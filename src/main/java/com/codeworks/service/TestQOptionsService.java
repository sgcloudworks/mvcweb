package com.codeworks.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.codeworks.model.TestQuestionOptions;

@Service
public interface TestQOptionsService {
	
	Map<Integer,List<TestQuestionOptions>> getAllQOptionsForTestId(int testId);
	Map<Integer,List<TestQuestionOptions>> getAllQOptionsForTestId(int testId,int langId);
	Map<String,List<TestQuestionOptions>> populateQNOptionsForATestAndLanguage(int testId,int langId);
	TestQuestionOptions getSpecificQuestionForTestId(int testId,int counter);

}
