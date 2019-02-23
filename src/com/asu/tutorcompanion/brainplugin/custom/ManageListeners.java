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
		ColumnEvaluation columnEvaluation = new ColumnEvaluation();
		InputModel input = columnEvaluation.generateInputFromMethod(methodCode);
		input.setAction(Constants.RUN_ACTION);
		
		Client client = new Client();
		Long inputId = client.saveInput(input);
		input.setId(inputId);
		
		TutorBrain tutorBrain = new TutorBrain();
		Gson gson = new Gson();
        String jsonString = gson.toJson(input);
        JSONObject inputJSON = new JSONObject(jsonString);
        System.out.println("a");
		JSONObject jObj = tutorBrain.getMessage(inputJSON);
		System.out.println("b");
//		input.setMessageCode(jObj.getInt("messageCode"));
//		input.setMessageGiven(jObj.getString("messageGiven"));
		input.setMessageCode(12);
		input.setMessageGiven("sdfdsfsdf");
		
		return input;
	}
	
	public InputModel debugRequest() throws CoreException, IOException, JSONException {
		ManageProject manageProject = new ManageProject();
		List<String> methodCode = manageProject.getStudentMethodFromFile();
		ColumnEvaluation columnEvaluation = new ColumnEvaluation();
		InputModel input = columnEvaluation.generateInputFromMethod(methodCode);
		input.setAction(Constants.DEBUG_ACTION);
		
		Client client = new Client();
		client.saveInput(input);
		
		TutorBrain tutorBrain = new TutorBrain();
		Gson gson = new Gson();
        String jsonString = gson.toJson(input);
        JSONObject inputJSON = new JSONObject(jsonString);
		JSONObject jObj = tutorBrain.getMessage(inputJSON);
		input.setMessageCode(jObj.getInt("messageCode"));
		input.setMessageGiven(jObj.getString("messageGiven"));
		
		return input;
	}
	
	public InputModel errorCaptureRequest(String error) throws IOException, CoreException, JSONException {
		ManageProject manageProject = new ManageProject();
		List<String> methodCode = manageProject.getStudentMethodFromFile();
		ColumnEvaluation columnEvaluation = new ColumnEvaluation();
		InputModel input = columnEvaluation.generateInputFromMethod(methodCode);
		input.setAction(Constants.ERROR_ACTION);
		input.setErrorType(error);
		
		Client client = new Client();
		client.saveInput(input);
		
		TutorBrain tutorBrain = new TutorBrain();
		Gson gson = new Gson();
        String jsonString = gson.toJson(input);
        JSONObject inputJSON = new JSONObject(jsonString);
		JSONObject jObj = tutorBrain.getMessage(inputJSON);
		input.setMessageCode(jObj.getInt("messageCode"));
		input.setMessageGiven(jObj.getString("messageGiven"));
		
		return input;
	}
	
	public InputModel helpRequest() throws CoreException, IOException, JSONException {
		ManageProject manageProject = new ManageProject();
		List<String> methodCode = manageProject.getStudentMethodFromFile();
		ColumnEvaluation columnEvaluation = new ColumnEvaluation();
		InputModel input = columnEvaluation.generateInputFromMethod(methodCode);
		input.setAction(Constants.HELP_ACTION);
		
		Client client = new Client();
		client.saveInput(input);
		
		TutorBrain tutorBrain = new TutorBrain();
		Gson gson = new Gson();
        String jsonString = gson.toJson(input);
        JSONObject inputJSON = new JSONObject(jsonString);
		JSONObject jObj = tutorBrain.getMessage(inputJSON);
		input.setMessageCode(jObj.getInt("messageCode"));
		input.setMessageGiven(jObj.getString("messageGiven"));
		
		return input;
	}
	
	public InputModel submitRequest() throws CoreException, IOException, JSONException {
		ManageProject manageProject = new ManageProject();
		List<String> methodCode = manageProject.getStudentMethodFromFile();
		ColumnEvaluation columnEvaluation = new ColumnEvaluation();
		InputModel input = columnEvaluation.generateInputFromMethod(methodCode);
		input.setAction(Constants.SUBMIT_ACTION);
		input.setAssignmentCompletedSuccessfully(1);
		
//		Client client = new Client();
//		client.saveInput(input);
//		
//		TutorBrain tutorBrain = new TutorBrain();
//		Gson gson = new Gson();
//        String jsonString = gson.toJson(input);
//        JSONObject inputJSON = new JSONObject(jsonString);
//		JSONObject jObj = tutorBrain.getMessage(inputJSON);
//		input.setMessageCode(jObj.getInt("messageCode"));
//		input.setMessageGiven(jObj.getString("messageGiven"));
		
		return input;
	}
}
