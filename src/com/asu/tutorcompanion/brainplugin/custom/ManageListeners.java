package com.asu.tutorcompanion.brainplugin.custom;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import thesis_neuroph.thesis_neuroph.TutorBrain;

public class ManageListeners {

	public InputModel runRequest() throws CoreException, IOException, JSONException {
		ManageProject manageProject = new ManageProject();
		List<String> methodCode = manageProject.getStudentMethodFromFile();
		
		// Repeat in all methods where this is created
		String courseName = methodCode.get(methodCode.size()-1); // course name
		int studentId = Integer.parseInt(methodCode.get(methodCode.size()-2)); // student ID
		methodCode.remove(methodCode.size()-1);
		methodCode.remove(methodCode.size()-1);
		//

		ColumnEvaluation columnEvaluation = new ColumnEvaluation();
		InputModel input = columnEvaluation.generateInputFromMethod(methodCode);
		input.setAction(Constants.RUN_ACTION);
		
		// Repeat where needed
		input.setStudentId(studentId);
		input.setCourseName(courseName);
		//
		
		// All methods in this file need this
		Client client = new Client();
		Long inputId = client.saveInput(input);
		input.setId(inputId);
		//
		
		TutorBrain tutorBrain = new TutorBrain();
		Gson gson = new Gson();
        String jsonString = gson.toJson(input);
        JSONObject inputJSON = new JSONObject(jsonString);
		JSONObject jObj = tutorBrain.getMessage(inputJSON);
		input.setMessageCode(jObj.getInt("messageCode"));
		input.setMessageGiven(jObj.getString("message"));
//		input.setMessageCode(12);
//		input.setMessageGiven("sdfdsfsdf");
		
		return input;
	}
	
	public InputModel debugRequest() throws CoreException, IOException, JSONException {
		ManageProject manageProject = new ManageProject();
		List<String> methodCode = manageProject.getStudentMethodFromFile();
		
		// Repeat in all methods where this is created
		String courseName = methodCode.get(methodCode.size()-1); // course name
		int studentId = Integer.parseInt(methodCode.get(methodCode.size()-2)); // student ID
		methodCode.remove(methodCode.size()-1);
		methodCode.remove(methodCode.size()-1);
		//

		ColumnEvaluation columnEvaluation = new ColumnEvaluation();
		InputModel input = columnEvaluation.generateInputFromMethod(methodCode);
		input.setAction(Constants.DEBUG_ACTION);
		
		// Repeat where needed
		input.setStudentId(studentId);
		input.setCourseName(courseName);
		//
		
		// All methods in this file need this
		Client client = new Client();
		Long inputId = client.saveInput(input);
		input.setId(inputId);
		//
		TutorBrain tutorBrain = new TutorBrain();
		Gson gson = new Gson();
        String jsonString = gson.toJson(input);
        JSONObject inputJSON = new JSONObject(jsonString);
		JSONObject jObj = tutorBrain.getMessage(inputJSON);
		input.setMessageCode(jObj.getInt("messageCode"));
		input.setMessageGiven(jObj.getString("message"));
		
		return input;
	}
	
	public InputModel errorCaptureRequest(String error) throws IOException, CoreException, JSONException {
		ManageProject manageProject = new ManageProject();
		List<String> methodCode = manageProject.getStudentMethodFromFile();
		
		// Repeat in all methods where this is created
		String courseName = methodCode.get(methodCode.size()-1); // course name
		int studentId = Integer.parseInt(methodCode.get(methodCode.size()-2)); // student ID
		methodCode.remove(methodCode.size()-1);
		methodCode.remove(methodCode.size()-1);
		//

		ColumnEvaluation columnEvaluation = new ColumnEvaluation();
		InputModel input = columnEvaluation.generateInputFromMethod(methodCode);
		input.setAction(Constants.ERROR_ACTION);
		
		// Repeat where needed
		input.setStudentId(studentId);
		input.setCourseName(courseName);
		//
		
		// All methods in this file need this
		Client client = new Client();
		Long inputId = client.saveInput(input);
		input.setId(inputId);
		//
		
		TutorBrain tutorBrain = new TutorBrain();
		Gson gson = new Gson();
        String jsonString = gson.toJson(input);
        JSONObject inputJSON = new JSONObject(jsonString);
		JSONObject jObj = tutorBrain.getMessage(inputJSON);
		input.setMessageCode(jObj.getInt("messageCode"));
		input.setMessageGiven(jObj.getString("message"));
		
		return input;
	}
	
	public InputModel helpRequest() throws CoreException, IOException, JSONException {
		ManageProject manageProject = new ManageProject();
		List<String> methodCode = manageProject.getStudentMethodFromFile();
		
		// Repeat in all methods where this is created
		String courseName = methodCode.get(methodCode.size()-1); // course name
		int studentId = Integer.parseInt(methodCode.get(methodCode.size()-2)); // student ID
		methodCode.remove(methodCode.size()-1);
		methodCode.remove(methodCode.size()-1);
		//

		ColumnEvaluation columnEvaluation = new ColumnEvaluation();
		InputModel input = columnEvaluation.generateInputFromMethod(methodCode);
		input.setAction(Constants.HELP_ACTION);
		
		// Repeat where needed
		input.setStudentId(studentId);
		input.setCourseName(courseName);
		//
		
		// All methods in this file need this
		Client client = new Client();
		Long inputId = client.saveInput(input);
		input.setId(inputId);
		//
		
		TutorBrain tutorBrain = new TutorBrain();
		Gson gson = new Gson();
        String jsonString = gson.toJson(input);
        JSONObject inputJSON = new JSONObject(jsonString);
		JSONObject jObj = tutorBrain.getMessage(inputJSON);
		input.setMessageCode(jObj.getInt("messageCode"));
		input.setMessageGiven(jObj.getString("message"));
		
		return input;
	}
	
	public InputModel submitRequest() throws CoreException, IOException, JSONException {
		ManageProject manageProject = new ManageProject();
		List<String> methodCode = manageProject.getStudentMethodFromFile();
		
		// Repeat in all methods where this is created
		String courseName = methodCode.get(methodCode.size()-1); // course name
		int studentId = Integer.parseInt(methodCode.get(methodCode.size()-2)); // student ID
		methodCode.remove(methodCode.size()-1);
		methodCode.remove(methodCode.size()-1);
		//

		ColumnEvaluation columnEvaluation = new ColumnEvaluation();
		InputModel input = columnEvaluation.generateInputFromMethod(methodCode);
		input.setAction(Constants.SUBMIT_ACTION);
		input.setAssignmentCompletedSuccessfully(1);
		
		// Repeat where needed
		input.setStudentId(studentId);
		input.setCourseName(courseName);
		//
		
		// All methods in this file need this
		Client client = new Client();
		Long inputId = client.saveInput(input);
		input.setId(inputId);
		//
		return input;
	}
}
