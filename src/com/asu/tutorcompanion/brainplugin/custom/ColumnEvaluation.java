package com.asu.tutorcompanion.brainplugin.custom;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ColumnEvaluation {
		
	public InputModel generateInputFromMethod(List<String> method) {
		int isComparator = 0;
		int isDouble = 0;
		int isFloat = 0;
		int isForWhileDo = 0;
		int isIf = 0;
		int isNew = 0;
		int isReturn = 0;
		int commentedLines = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String submissionDate = sdf.format(new Date());
		
		for (int i = 0; i < method.size(); i++) {
			if (method.get(i).startsWith("//")) {
				commentedLines += 1;
			}
			if (!method.get(i).startsWith("//") && method.get(i).toLowerCase().contains("comparator")) {
				isComparator = 1;
			}
			if (!method.get(i).startsWith("//") && method.get(i).toLowerCase().contains("double")) {
				isDouble = 1;
			}
			if (!method.get(i).startsWith("//") && method.get(i).toLowerCase().contains("float")) {
				isFloat = 1;
			}
			if (!method.get(i).startsWith("//") && (method.get(i).toLowerCase().contains("for") || 
					method.get(i).toLowerCase().contains("do") || method.get(i).toLowerCase().contains("while"))) {
				isForWhileDo = 1;
			}
			if (!method.get(i).startsWith("//") && method.get(i).toLowerCase().contains("if")) {
				isIf = 1;
			}
			if (!method.get(i).startsWith("//") && method.get(i).toLowerCase().contains("new")) {
				isNew = 1;
			}
			if (!method.get(i).startsWith("//") && method.get(i).toLowerCase().contains("return")) {
				isReturn = 1;
			}
		}
		
		InputModel input = new InputModel();
		input.setAssignmentName(Constants.ASSIGNMENT_NAME);
		input.setCyclomaticComplexity(calculateMethodCC(method));
		input.setSubmissionDateTime(submissionDate);
		input.setLinesOfCodeTotal(method.size());
		input.setKeywordComparatorFound(isComparator);
		input.setKeywordDoubleFound(isDouble);
		input.setKeyWordFloatFound(isFloat);
		input.setKeywordForWhileDoFound(isForWhileDo);
		input.setKeywordIfFound(isIf);
		input.setKeywordNewFound(isNew);
		input.setKeywordReturnFound(isReturn);
		input.setNumberOfCommentLines(commentedLines);
		
		return input;
	}

	// Calculates the cyclomatic complexity of a list of strings
	private int calculateMethodCC(List<String> method) {
		int cc = 0;
		
		for (int i = 0; i < method.size(); i++) {
			// Each return that isn't the last statement of a method.
			if (method.get(i).contains("return") && (i != (method.size() - 1))) {
				cc += 1;
			}
			// if, else, case, default.
			if (method.get(i).contains("if") || method.get(i).contains("else") || 
					method.get(i).contains("case") || method.get(i).contains("default")) {
				cc += 1;
			}
			// for, while, do-while, break, and continue.
			if (method.get(i).contains("for") || method.get(i).contains("while") ||
					method.get(i).contains("break") || method.get(i).contains("continue")) {
				cc += 1;
			}
			// &&  ||   ?  :
			if (method.get(i).contains("&&") || method.get(i).contains("||") ||
					method.get(i).contains("?") || method.get(i).contains(":")) {
				cc += 1;
			}
			// catch, finally, throw, or throws clause.
			if (method.get(i).contains("catch") || method.get(i).contains("finally") ||
					method.get(i).contains("throw") || method.get(i).contains("throws")) {
				cc += 1;
			}
		}
		
		return cc;
	}
}
