package com.asu.tutorcompanion.brainplugin.custom;

import java.io.Serializable;

public class InputModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long id;
	private int studentId;
	private String action;
	private String assignmentName;
	private int linesOfCodeTotal;
	private int keywordComparatorFound;
	private int keywordNewFound;
	private int keywordDoubleFound;
	private int keyWordFloatFound;
	private int keywordIfFound;
	private int keywordForWhileDoFound;
	private int keywordReturnFound;
	private int numberOfCommentLines;
	private String errorType;
	private String submissionDateTime;
	private int assignmentCompletedSuccessfully;
	private String messageGiven;
	private int messageCode;
	private int feedbackSurvey;
	private int cyclomaticComplexity;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getAssignmentName() {
		return assignmentName;
	}
	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}
	public int getLinesOfCodeTotal() {
		return linesOfCodeTotal;
	}
	public void setLinesOfCodeTotal(int linesOfCodeTotal) {
		this.linesOfCodeTotal = linesOfCodeTotal;
	}
	public int getKeywordComparatorFound() {
		return keywordComparatorFound;
	}
	public void setKeywordComparatorFound(int keywordComparatorFound) {
		this.keywordComparatorFound = keywordComparatorFound;
	}
	public int getKeywordNewFound() {
		return keywordNewFound;
	}
	public void setKeywordNewFound(int keywordNewFound) {
		this.keywordNewFound = keywordNewFound;
	}
	public int getKeywordDoubleFound() {
		return keywordDoubleFound;
	}
	public void setKeywordDoubleFound(int keywordDoubleFound) {
		this.keywordDoubleFound = keywordDoubleFound;
	}
	public int getKeyWordFloatFound() {
		return keyWordFloatFound;
	}
	public void setKeyWordFloatFound(int keyWordFloatFound) {
		this.keyWordFloatFound = keyWordFloatFound;
	}
	public int getKeywordIfFound() {
		return keywordIfFound;
	}
	public void setKeywordIfFound(int keywordIfFound) {
		this.keywordIfFound = keywordIfFound;
	}
	public int getKeywordForWhileDoFound() {
		return keywordForWhileDoFound;
	}
	public void setKeywordForWhileDoFound(int keywordForWhileDoFound) {
		this.keywordForWhileDoFound = keywordForWhileDoFound;
	}
	public int getKeywordReturnFound() {
		return keywordReturnFound;
	}
	public void setKeywordReturnFound(int keywordReturnFound) {
		this.keywordReturnFound = keywordReturnFound;
	}
	public int getNumberOfCommentLines() {
		return numberOfCommentLines;
	}
	public void setNumberOfCommentLines(int numberOfCommentLines) {
		this.numberOfCommentLines = numberOfCommentLines;
	}
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	public String getSubmissionDateTime() {
		return submissionDateTime;
	}
	public void setSubmissionDateTime(String submissionDateTime) {
		this.submissionDateTime = submissionDateTime;
	}
	public int getAssignmentCompletedSuccessfully() {
		return assignmentCompletedSuccessfully;
	}
	public void setAssignmentCompletedSuccessfully(int assignmentCompletedSuccessfully) {
		this.assignmentCompletedSuccessfully = assignmentCompletedSuccessfully;
	}
	public String getMessageGiven() {
		return messageGiven;
	}
	public void setMessageGiven(String messageGiven) {
		this.messageGiven = messageGiven;
	}
	public int getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(int messageCode) {
		this.messageCode = messageCode;
	}
	public int getFeedbackSurvey() {
		return feedbackSurvey;
	}
	public void setFeedbackSurvey(int feedbackSurvey) {
		this.feedbackSurvey = feedbackSurvey;
	}
	public int getCyclomaticComplexity() {
		return cyclomaticComplexity;
	}
	public void setCyclomaticComplexity(int cyclomaticComplexity) {
		this.cyclomaticComplexity = cyclomaticComplexity;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
