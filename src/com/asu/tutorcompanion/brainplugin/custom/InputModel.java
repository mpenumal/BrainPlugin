package com.asu.tutorcompanion.brainplugin.custom;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class InputModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int NumberOfMethods;
	private int KeywordMainFound;
	private int LinesOfCodeTotal;
	private int KeywordComparatorFound;
	private int KeywordNewFound;
	private int KeywordDoubleFound;
	private int KeyWordFloatFound;
	private int KeywordIfFound;
	private int KeywordForWhileDoFound;
	private int KeywordReturnFound;
	private int HelpButtonClicked;
	private int NumberOfCommentLines;
	private int LinesOfCodeChangedSinceLastRun;
	private int CyclomaticComplexity;
	private String ErrorType;
	private int ErrorTotal;
	private int ErrorsResolvedTotal;
	private int NumberRunAttempts;
	private int RunAttemptsSinceLastHint;
	private Time SubmissionTimestamp;
	private Date SubmissionDate;
	private float TimerValue;
	private float TimeSinceLastRun;
	private float TimeIdle;
	private float TimeTotal;
	private float TimeWorking;
	private float TimeWithErrors;
	private float TimeUntilErrorFixed;
	private float TimeSinceLastHint;
	private int AssignmentCompleted;
	private int AssignmentCompletedSuccessfully;
	private int ErrorCountSinceLastHint;
	private float TimeLastEncouragement;
	private float TimeMostRecentHint;
	private float TimeSecondMostRecentHint;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumberOfMethods() {
		return NumberOfMethods;
	}
	public void setNumberOfMethods(int numberOfMethods) {
		NumberOfMethods = numberOfMethods;
	}
	public int getKeywordMainFound() {
		return KeywordMainFound;
	}
	public void setKeywordMainFound(int keywordMainFound) {
		KeywordMainFound = keywordMainFound;
	}
	public int getLinesOfCodeTotal() {
		return LinesOfCodeTotal;
	}
	public void setLinesOfCodeTotal(int linesOfCodeTotal) {
		LinesOfCodeTotal = linesOfCodeTotal;
	}
	public int getKeywordComparatorFound() {
		return KeywordComparatorFound;
	}
	public void setKeywordComparatorFound(int keywordComparatorFound) {
		KeywordComparatorFound = keywordComparatorFound;
	}
	public int getKeywordNewFound() {
		return KeywordNewFound;
	}
	public void setKeywordNewFound(int keywordNewFound) {
		KeywordNewFound = keywordNewFound;
	}
	public int getKeywordDoubleFound() {
		return KeywordDoubleFound;
	}
	public void setKeywordDoubleFound(int keywordDoubleFound) {
		KeywordDoubleFound = keywordDoubleFound;
	}
	public int getKeyWordFloatFound() {
		return KeyWordFloatFound;
	}
	public void setKeyWordFloatFound(int keyWordFloatFound) {
		KeyWordFloatFound = keyWordFloatFound;
	}
	public int getKeywordIfFound() {
		return KeywordIfFound;
	}
	public void setKeywordIfFound(int keywordIfFound) {
		KeywordIfFound = keywordIfFound;
	}
	public int getKeywordForWhileDoFound() {
		return KeywordForWhileDoFound;
	}
	public void setKeywordForWhileDoFound(int keywordForWhileDoFound) {
		KeywordForWhileDoFound = keywordForWhileDoFound;
	}
	public int getKeywordReturnFound() {
		return KeywordReturnFound;
	}
	public void setKeywordReturnFound(int keywordReturnFound) {
		KeywordReturnFound = keywordReturnFound;
	}
	public int getHelpButtonClicked() {
		return HelpButtonClicked;
	}
	public void setHelpButtonClicked(int helpButtonClicked) {
		HelpButtonClicked = helpButtonClicked;
	}
	public int getNumberOfCommentLines() {
		return NumberOfCommentLines;
	}
	public void setNumberOfCommentLines(int numberOfCommentLines) {
		NumberOfCommentLines = numberOfCommentLines;
	}
	public int getLinesOfCodeChangedSinceLastRun() {
		return LinesOfCodeChangedSinceLastRun;
	}
	public void setLinesOfCodeChangedSinceLastRun(int linesOfCodeChangedSinceLastRun) {
		LinesOfCodeChangedSinceLastRun = linesOfCodeChangedSinceLastRun;
	}
	public int getCyclomaticComplexity() {
		return CyclomaticComplexity;
	}
	public void setCyclomaticComplexity(int cyclomaticComplexity) {
		CyclomaticComplexity = cyclomaticComplexity;
	}
	public String getErrorType() {
		return ErrorType;
	}
	public void setErrorType(String errorType) {
		ErrorType = errorType;
	}
	public int getErrorTotal() {
		return ErrorTotal;
	}
	public void setErrorTotal(int errorTotal) {
		ErrorTotal = errorTotal;
	}
	public int getErrorsResolvedTotal() {
		return ErrorsResolvedTotal;
	}
	public void setErrorsResolvedTotal(int errorsResolvedTotal) {
		ErrorsResolvedTotal = errorsResolvedTotal;
	}
	public int getNumberRunAttempts() {
		return NumberRunAttempts;
	}
	public void setNumberRunAttempts(int numberRunAttempts) {
		NumberRunAttempts = numberRunAttempts;
	}
	public int getRunAttemptsSinceLastHint() {
		return RunAttemptsSinceLastHint;
	}
	public void setRunAttemptsSinceLastHint(int runAttemptsSinceLastHint) {
		RunAttemptsSinceLastHint = runAttemptsSinceLastHint;
	}
	public Time getSubmissionTimestamp() {
		return SubmissionTimestamp;
	}
	public void setSubmissionTimestamp(Time submissionTimestamp) {
		SubmissionTimestamp = submissionTimestamp;
	}
	public Date getSubmissionDate() {
		return SubmissionDate;
	}
	public void setSubmissionDate(Date submissionDate) {
		SubmissionDate = submissionDate;
	}
	public float getTimerValue() {
		return TimerValue;
	}
	public void setTimerValue(float timerValue) {
		TimerValue = timerValue;
	}
	public float getTimeSinceLastRun() {
		return TimeSinceLastRun;
	}
	public void setTimeSinceLastRun(float timeSinceLastRun) {
		TimeSinceLastRun = timeSinceLastRun;
	}
	public float getTimeIdle() {
		return TimeIdle;
	}
	public void setTimeIdle(float timeIdle) {
		TimeIdle = timeIdle;
	}
	public float getTimeTotal() {
		return TimeTotal;
	}
	public void setTimeTotal(float timeTotal) {
		TimeTotal = timeTotal;
	}
	public float getTimeWorking() {
		return TimeWorking;
	}
	public void setTimeWorking(float timeWorking) {
		TimeWorking = timeWorking;
	}
	public float getTimeWithErrors() {
		return TimeWithErrors;
	}
	public void setTimeWithErrors(float timeWithErrors) {
		TimeWithErrors = timeWithErrors;
	}
	public float getTimeUntilErrorFixed() {
		return TimeUntilErrorFixed;
	}
	public void setTimeUntilErrorFixed(float timeUntilErrorFixed) {
		TimeUntilErrorFixed = timeUntilErrorFixed;
	}
	public float getTimeSinceLastHint() {
		return TimeSinceLastHint;
	}
	public void setTimeSinceLastHint(float timeSinceLastHint) {
		TimeSinceLastHint = timeSinceLastHint;
	}
	public int getAssignmentCompleted() {
		return AssignmentCompleted;
	}
	public void setAssignmentCompleted(int assignmentCompleted) {
		AssignmentCompleted = assignmentCompleted;
	}
	public int getAssignmentCompletedSuccessfully() {
		return AssignmentCompletedSuccessfully;
	}
	public void setAssignmentCompletedSuccessfully(int assignmentCompletedSuccessfully) {
		AssignmentCompletedSuccessfully = assignmentCompletedSuccessfully;
	}
	public int getErrorCountSinceLastHint() {
		return ErrorCountSinceLastHint;
	}
	public void setErrorCountSinceLastHint(int errorCountSinceLastHint) {
		ErrorCountSinceLastHint = errorCountSinceLastHint;
	}
	public float getTimeLastEncouragement() {
		return TimeLastEncouragement;
	}
	public void setTimeLastEncouragement(float timeLastEncouragement) {
		TimeLastEncouragement = timeLastEncouragement;
	}
	public float getTimeMostRecentHint() {
		return TimeMostRecentHint;
	}
	public void setTimeMostRecentHint(float timeMostRecentHint) {
		TimeMostRecentHint = timeMostRecentHint;
	}
	public float getTimeSecondMostRecentHint() {
		return TimeSecondMostRecentHint;
	}
	public void setTimeSecondMostRecentHint(float timeSecondMostRecentHint) {
		TimeSecondMostRecentHint = timeSecondMostRecentHint;
	}
}
