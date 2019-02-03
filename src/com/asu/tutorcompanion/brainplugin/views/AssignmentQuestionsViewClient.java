package com.asu.tutorcompanion.brainplugin.views;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.asu.tutorcompanion.brainplugin.launching.TutorPluginLogTracker;
import com.asu.tutorcompanion.brainplugin.model.Input;
import com.asu.tutorcompanion.brainplugin.splashHandlers.InteractiveSplashHandler;

public class AssignmentQuestionsViewClient {
	
	/** Get Assignments from Plugin
	 * @throws IOException 
	 * 
	 */
	public void getAssignmentsFromPlugin() throws IOException {
		String directoryPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + 
				File.separator + "AssignmentList_Cosmo_Client";
		
		String userCourseName = InteractiveSplashHandler.login_courseName;
		
		if (userCourseName == null || userCourseName.isEmpty()) {
			userCourseName = "ASUCSE";
		}
		
		// Assignment 1 Setup
		String assignment1 = getAssignment1();
		String assingment1_name = "ConditionalStatement.java";
		String assignment1_startDate = "2/3/2019";
		String assignment1_endDate = "2/23/2019";
		File assignment1_dest = new File(directoryPath + File.separator + assingment1_name);
		if (!assignment1_dest.exists() || assignment1_dest.isDirectory()) {
			try (FileWriter file = new FileWriter(directoryPath + File.separator + assingment1_name)) {
				file.write(assignment1);
				file.close();
			}
		} else {
			System.out.println("File "+ assingment1_name +" Already Exists. So did not replace.");
		}
		File assignment1_metadata = new File(directoryPath + File.separator + assingment1_name.split("\\.")[0] + "_" + "metadata" + ".txt");
		if (!assignment1_metadata.exists() || assignment1_metadata.isDirectory()) {
			try (FileWriter file = new FileWriter(directoryPath + File.separator + assingment1_name.split("\\.")[0] + "_" + "metadata" + ".txt")) {
				String str = "AssignmentName--"+assingment1_name.split("\\.")[0]+System.lineSeparator();
				//str += "CourseName--"+courseName+System.lineSeparator();
				str += "StartDate--"+assignment1_startDate+System.lineSeparator();
				str += "EndDate--"+assignment1_endDate+System.lineSeparator();
				file.write(str);
				file.close();
				//System.out.println("Successfully Copied JSON Object to File...");
				//System.out.println("\nJSON Object: " + jObj);
			}
		}
		else {
			System.out.println("File "+ assingment1_name.split("\\.")[0] + "_" + "metadata" + ".txt" +" Already Exists. So did not replace.");
		}
		
		// Assignment 2 setup
		String assignment2 = getAssignment2();
		String assingment2_name = "Recursion.java";
		String assignment2_startDate = "2/3/2019";
		String assignment2_endDate = "2/23/2019";
		File assignment2_dest = new File(directoryPath + File.separator + assingment2_name);
		if (!assignment2_dest.exists() || assignment2_dest.isDirectory()) {
			try (FileWriter file = new FileWriter(directoryPath + File.separator + assingment2_name)) {
				file.write(assignment2);
				file.close();
			}
		} else {
			System.out.println("File "+ assingment1_name +" Already Exists. So did not replace.");
		}
		File assignment2_metadata = new File(directoryPath + File.separator + assingment2_name.split("\\.")[0] + "_" + "metadata" + ".txt");
		if (!assignment2_metadata.exists() || assignment2_metadata.isDirectory()) {
			try (FileWriter file = new FileWriter(directoryPath + File.separator + assingment2_name.split("\\.")[0] + "_" + "metadata" + ".txt")) {
				String str = "AssignmentName--"+assingment2_name.split("\\.")[0]+System.lineSeparator();
				//str += "CourseName--"+courseName+System.lineSeparator();
				str += "StartDate--"+assignment2_startDate+System.lineSeparator();
				str += "EndDate--"+assignment2_endDate+System.lineSeparator();
				file.write(str);
				file.close();
				//System.out.println("Successfully Copied JSON Object to File...");
				//System.out.println("\nJSON Object: " + jObj);
			}
		}
		else {
			System.out.println("File "+ assingment1_name.split("\\.")[0] + "_" + "metadata" + ".txt" +" Already Exists. So did not replace.");
		}
	}
	
	private String getAssignment1() {
		return "public class ConditionalStatement {\r\n" + 
				"	public static void main(String[] args) {\r\n" + 
				"        System.out.println(\"Hello, World!\");\r\n" + 
				"   }" + 
				"\r\n" + 
				"//	Foo Corporation needs a program to calculate how much to pay their hourly employees. The US Department of Labor requires that employees get paid time and a half for any hours over 40 that they work in a single week. For example, if an employee works 45 hours, they get 5 hours of overtime, at 1.5 times their base pay. The State of Massachusetts requires that hourly employees be paid at least $8.00 an hour. Foo Corp requires that an employee not work more than 60 hours in a week.\r\n" + 
				"//	Summary of Rules\r\n" + 
				"//	• An employee gets paid (hours worked) × (base pay), for each hour up to 40 hours.\r\n" + 
				"//	• For every hour over 40, they get overtime = (base pay) × 1.5.\r\n" + 
				"//	• The base pay must not be less than the minimum wage ($8.00 an hour). If it is, print an error.\r\n" + 
				"//	• If the number of hours is greater than 60, print an error message.\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"";
	}
	
	private String getAssignment2() {
		return "public class Recursion {\r\n" + 
				"	public static void main(String[] args) {\r\n" + 
				"        System.out.println(\"Hello, World!\");\r\n" + 
				"   }" + 
				"	\r\n" + 
				"//	For the following problem, you may not use FOR, WHILE, DO, or any other looping construct. \r\n" + 
				"//	Write static separate methods to Compute the length of an IntList.\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"";
	}
	
	/** 
	 * Send captured events to API
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public void sendInput(Input input) throws IOException, ParseException {
		String directoryPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + 
				File.separator + "AssignmentList_Cosmo_Client";

		String studentId = InteractiveSplashHandler.login_userName;
		String courseName = InteractiveSplashHandler.login_courseName;
		String assignmentName = TutorPluginLogTracker.assignmentName;
		
		// For local test
	    studentId = "1122334455";
	    courseName = "ASUCSE";
	    
	    input.setId(Integer.parseInt(studentId));
	    
	    File metaDataFile = new File(directoryPath + File.separator + assignmentName + "_" + courseName + "_" + "metadata" + ".txt");
		if (metaDataFile.exists() && !metaDataFile.isDirectory()) {
			List<String> metaData = Files.readAllLines(metaDataFile.toPath());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date startDate = sdf.parse(metaData.get(2).split("--")[1]);
			Date endDate = sdf.parse(metaData.get(3).split("--")[1]);
			Date currentDate = new Date();
			
			if ((currentDate.equals(startDate) || currentDate.after(startDate)) &&
					(currentDate.equals(endDate) || currentDate.before(endDate))) 
			{
				// Local machine URL
				URL url = new URL("http://localhost:8080/server/inputs/" + input.getId());
				// Manohar AWS URL
				//URL url = new URL("http://34.224.41.66:8080/assignmentResults");
				
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				
				ObjectOutputStream out = new ObjectOutputStream(conn.getOutputStream());
				out.writeObject(input);
				out.close();

				if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
					throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
				}
				conn.disconnect();
			}
		}
		else {
			System.out.println("File "+ assignmentName + "_" + courseName + "_" + "metadata" + ".txt" +" does not Exists.");
			System.out.println("Cannot send info to server.");
		}
	}

	/**
	 * Get Assignments using API 
	 * @throws IOException
	 * @throws ParseException 
	 * @throws JSONException 
	 */
	public void getAssignmentsClient() throws IOException, ParseException, JSONException {
		
		String directoryPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + 
									File.separator + "AssignmentList_Cosmo_Client";
		
		String userCourseName = InteractiveSplashHandler.login_courseName;
		
		// For local test
		userCourseName = "CSE360";
		
		// Local machine URL
		URL url = new URL("http://localhost:8080/assignments");
		// Manohar AWS URL
		//URL url = new URL("http://34.224.41.66:8080/assignments");
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output;
		JSONArray jObjList = new JSONArray();
		int jsonCount = 0;
		
		while ((output = br.readLine()) != null) {
			jObjList = (JSONArray) new JSONTokener(output).nextValue();
		}
		
		if (jObjList != null && jObjList.length() > 0) {
			while (jsonCount < jObjList.length()) {
				JSONObject jObj = jObjList.getJSONObject(jsonCount);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				
				String assignmentName = jObj.getString("assignmentName");
				String courseName = jObj.getString("courseName");
				String tempStartDate = jObj.getString("startDate");
				Date startDate = sdf.parse(tempStartDate);
				String tempEndDate = jObj.getString("endDate");
				Date endDate = sdf.parse(tempEndDate);
				String fileType = jObj.getString("fileType");
				JSONArray codeFile = jObj.getJSONArray("codeFile");
				
				Date currentDate = new Date();
				
				String fileExtension = ".java";
				if (fileType.equals("JAVA")) {
					fileExtension = ".java";
				}
				else if (fileType.equals("TEXT")) {
					fileExtension = ".txt";
				}
				
				if (!assignmentName.equals("Assignment00") && (userCourseName.equals(courseName)) 
						&& (currentDate.equals(startDate) || currentDate.after(startDate)) 
						&& (currentDate.before(endDate) || currentDate.equals(endDate))) {
					File temp1 = new File(directoryPath + File.separator + assignmentName + fileExtension);
					if (!temp1.exists() || temp1.isDirectory()) {
						try (FileWriter file = new FileWriter(directoryPath + File.separator + assignmentName + fileExtension)) {
							String str = "";
							for (int count = 0; count < codeFile.length(); count++) {
								str += (codeFile.getString(count));
								str += System.lineSeparator();
							}
							file.write(str);
							file.close();
							//System.out.println("Successfully Copied JSON Object to File...");
							//System.out.println("\nJSON Object: " + jObj);
						}
					}
					else {
						System.out.println("File "+ assignmentName + fileExtension +" Already Exists. So did not replace.");
					}
					
					File temp2 = new File(directoryPath + File.separator + assignmentName + "_" + courseName + "_" + "metadata" + ".txt");
					if (!temp2.exists() || temp2.isDirectory()) {
						try (FileWriter file = new FileWriter(directoryPath + File.separator + 
																assignmentName + "_" + courseName + "_" + "metadata" + ".txt")) {
							String str = "AssignmentName--"+assignmentName+System.lineSeparator();
							str += "CourseName--"+courseName+System.lineSeparator();
							str += "StartDate--"+tempStartDate+System.lineSeparator();
							str += "EndDate--"+tempEndDate+System.lineSeparator();
							file.write(str);
							file.close();
							//System.out.println("Successfully Copied JSON Object to File...");
							//System.out.println("\nJSON Object: " + jObj);
						}
					}
					else {
						System.out.println("File "+ assignmentName + "_" + courseName + "_" + "metadata" + ".txt" +" Already Exists. So did not replace.");
					}
				}
				
				jsonCount++;
			}
		}
		
		conn.disconnect();
	}
	
	/**
	 * Send log using API
	 * @throws IOException
	 * @throws ParseException 
	 * @throws JSONException 
	 */
	public void sendLogClient(List<String> lines) throws IOException, ParseException, JSONException {
		String directoryPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + 
				File.separator + "AssignmentList_Cosmo_Client";

		String studentId = InteractiveSplashHandler.login_userName;
		String courseName = InteractiveSplashHandler.login_courseName;
		String assignmentName = TutorPluginLogTracker.assignmentName;
		List<String> outputFile = lines;
		
		// For local test
	    studentId = "1111111111";
	    courseName = "CSE360";
		
		File metaDataFile = new File(directoryPath + File.separator + assignmentName + "_" + courseName + "_" + "metadata" + ".txt");
		if (metaDataFile.exists() && !metaDataFile.isDirectory()) {
			List<String> metaData = Files.readAllLines(metaDataFile.toPath());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date startDate = sdf.parse(metaData.get(2).split("--")[1]);
			Date endDate = sdf.parse(metaData.get(3).split("--")[1]);
			Date currentDate = new Date();
			
			if ((currentDate.equals(startDate) || currentDate.after(startDate)) &&
					(currentDate.equals(endDate) || currentDate.before(endDate))) 
			{
				JSONObject jObj = new JSONObject();
				jObj.put("studentId", studentId);
				jObj.put("courseName", courseName);
				jObj.put("assignmentName", assignmentName);
				jObj.put("outputFile", outputFile);
				
				// Local machine URL
				URL url = new URL("http://localhost:8080/assignmentResults");
				// Manohar AWS URL
				//URL url = new URL("http://34.224.41.66:8080/assignmentResults");
				
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");

				String input = jObj.toString();
				
				OutputStream os = conn.getOutputStream();
				os.write(input.getBytes());
				os.flush();

				if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
					throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
				}
				conn.disconnect();
			}
		}
		else {
			System.out.println("File "+ assignmentName + "_" + courseName + "_" + "metadata" + ".txt" +" does not Exists.");
			System.out.println("Cannot send info to server.");
		}
	}
	
}
