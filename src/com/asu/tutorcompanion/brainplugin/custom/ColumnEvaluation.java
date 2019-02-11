package com.asu.tutorcompanion.brainplugin.custom;

import java.util.List;

public class ColumnEvaluation {
	
	// Calculates the cyclomatic complexity of a list of strings
	public int calculateMethodCC(List<String> method) {
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
