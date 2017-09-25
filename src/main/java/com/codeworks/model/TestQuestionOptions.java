package com.codeworks.model;

public class TestQuestionOptions {

	private int languageId;
	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	private int testId;
	private int questionId;
	//private int optionId;
	private StringBuffer question;
	private StringBuffer options[] = new StringBuffer[4];	
	private String correctAnswer;
	private StringBuffer explanationSteps;
	private StringBuffer referenceLink;
	private String isActive;
	private boolean lastQuestion = false;
	private boolean firstQuestion = false;
	
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public StringBuffer getQuestion() {
		return question;
	}

	public void setQuestion(StringBuffer question) {
		this.question = question;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public StringBuffer getExplanationSteps() {
		return explanationSteps;
	}

	public void setExplanationSteps(StringBuffer explanationSteps) {
		this.explanationSteps = explanationSteps;
	}

	public StringBuffer getReferenceLink() {
		return referenceLink;
	}

	public void setReferenceLink(StringBuffer referenceLink) {
		this.referenceLink = referenceLink;
	}

	/*public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}*/

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public StringBuffer[] getOptions() {
		return options;
	}

	public void setOptions(StringBuffer[] options) {
		this.options = options;
	}
	
	public boolean isLastQuestion() {
		return lastQuestion;
	}

	public void setLastQuestion(boolean lastQuestion) {
		this.lastQuestion = lastQuestion;
	}

	public boolean isFirstQuestion() {
		return firstQuestion;
	}

	public void setFirstQuestion(boolean isFirstQuestion) {
		this.firstQuestion = isFirstQuestion;
	}

}
