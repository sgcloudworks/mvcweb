package com.codeworks.mvcweb;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeworks.model.TestQuestionOptions;
import com.codeworks.service.TestQOptionsService;

@RestController
@RequestMapping("/userTests")
@Secured("ROLE_USER")
public class TestQOptionsController {
	
	@Autowired(required=true)
	private TestQOptionsService testQOptionsService;

	
	@Context
    private HttpServletRequest httpRequest;
	
	
	
	/*@GetMapping("/tests")
	public List getAllTests() {
		return testDetailService.loadAllTests();
	}*/
	
	@GetMapping("getAllQNOptionsForATest/{id}")
	public ResponseEntity<List<TestQuestionOptions>> getAllQNOptionsForATest(@PathVariable("id") Integer id) {

		List<TestQuestionOptions> testQuestionOptionsList = testQOptionsService.getAllQOptionsForTestId(id).get(id);
		if (testQuestionOptionsList.isEmpty()) {
			return new ResponseEntity("No Questions found for ID " + id, HttpStatus.NOT_FOUND);
		}
		
		Collections.shuffle(testQuestionOptionsList);
		testQuestionOptionsList.get(0).setFirstQuestion(true);
		int listSize = testQuestionOptionsList.size();
		testQuestionOptionsList.get(listSize-1).setLastQuestion(true);
		return new ResponseEntity(testQuestionOptionsList, HttpStatus.OK);
	}

	
	/*@RequestMapping(value="/getAllQNOptionsForATestAndLanguage",method=RequestMethod.GET)
	public ResponseEntity<List<TestQuestionOptions>> getAllQNOptionsForATest1(@RequestParam("testId") Integer testId,@RequestParam("languageId") Integer langId,HttpServletRequest request) {
		
		List<TestQuestionOptions> testQuestionOptionsList = testQOptionsService.popTestQs(testId, langId).get(""+testId+langId);
		
		//List<TestQuestionOptions> testQuestionOptionsList = testQOptionsService.getAllQOptionsForTestId(testId, langId).get(testId);
		if (testQuestionOptionsList.isEmpty()) {
			return new ResponseEntity("No Questions found for ID " + testId, HttpStatus.NOT_FOUND);
		}
		
		
		Collections.shuffle(testQuestionOptionsList);
		testQuestionOptionsList.get(0).setFirstQuestion(true);
		int listSize = testQuestionOptionsList.size();
		testQuestionOptionsList.get(listSize-1).setLastQuestion(true);
		return new ResponseEntity(testQuestionOptionsList, HttpStatus.OK);
	}*/
	
	@RequestMapping(value="/getAllQNOptionsForATestAndLanguage",method=RequestMethod.GET)
	
	public ResponseEntity<List<TestQuestionOptions>> anotherTestMethod(@RequestParam("testId") Integer testId,@RequestParam("languageId") Integer langId,HttpServletRequest request) {
		
		
		List<TestQuestionOptions> testQuestionOptionsList = (testQOptionsService.populateQNOptionsForATestAndLanguage(testId, langId)).get(""+testId+""+langId);
		if (testQuestionOptionsList.isEmpty()) {
			return new ResponseEntity("No Questions found for ID " + testId, HttpStatus.NOT_FOUND);
		}
		
		
		Collections.shuffle(testQuestionOptionsList);
		testQuestionOptionsList.get(0).setFirstQuestion(true);
		int listSize = testQuestionOptionsList.size();
		testQuestionOptionsList.get(listSize-1).setLastQuestion(true);
		return new ResponseEntity(testQuestionOptionsList, HttpStatus.OK);
	}
	
	@GetMapping("/getQuestionFromTestId/{id}/{counter}")
	public ResponseEntity<TestQuestionOptions> getQuestionFromTestId(@PathVariable("id") Integer id,@PathVariable("counter") Integer counter ) {

		TestQuestionOptions testQuestionOptions = testQOptionsService.getSpecificQuestionForTestId(id, counter);
		
		if (testQuestionOptions == null) {
			return new ResponseEntity("No Test Detail found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<TestQuestionOptions>(testQuestionOptions, HttpStatus.OK);
	}

}
