package com.asu.tutorcompanion.brainplugin.custom;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.resources.ResourcesPlugin;

import com.asu.tutorcompanion.brainplugin.launching.TutorPluginLogTracker;
import com.asu.tutorcompanion.brainplugin.splashHandlers.InteractiveSplashHandler;
import com.google.gson.Gson;

public class Client {
	
	/** Get Assignment from PlugIn
	 * @throws IOException 
	 * 
	 */
	public void getAssignmentFromPlugin() throws IOException {
		String assignment = Constants.ASSIGNMENT;
		String assignmentName = Constants.ASSIGNMENT_NAME;
		String fileType = Constants.FILE_TYPE;
		String downloadFolder = Constants.DOWNLOAD_FOLDER_NAME;
		String courseName = Constants.DEFAULT_COURSE_NAME;
		
		String directoryPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + 
				File.separator + downloadFolder;
		
		// Comment when using locally
		courseName = InteractiveSplashHandler.login_courseName;
		
		// Assignment Setup
		File destination = new File(directoryPath + File.separator + assignmentName + fileType);
		if (!destination.exists() || destination.isDirectory()) {
			try (FileWriter file = new FileWriter(directoryPath + File.separator + assignmentName + fileType)) {
				file.write(assignment);
				file.close();
			}
		} else {
			System.out.println("File "+ assignmentName + fileType +" Already Exists. So did not replace.");
		}
	}
	
	/** 
	 * Send captured events to API
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public void sendInput(InputModel input) throws IOException, ParseException {
		String assignment = Constants.ASSIGNMENT;
		String assignmentName = Constants.ASSIGNMENT_NAME;
		String fileType = Constants.FILE_TYPE;
		String downloadFolder = Constants.DOWNLOAD_FOLDER_NAME;
		String studentId = Constants.DEFAULT_STUDENT_ID;
		String courseName = Constants.DEFAULT_COURSE_NAME;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date startDate = sdf.parse(Constants.ASSIGNMENT_STARTDATE);
		Date endDate = sdf.parse(Constants.ASSIGNMENT_ENDDATE);
		Date currentDate = new Date();
		
		String directoryPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + 
				File.separator + downloadFolder;
		
		// Comment when using locally
		studentId = InteractiveSplashHandler.login_userName;
		courseName = InteractiveSplashHandler.login_courseName;
		assignmentName = TutorPluginLogTracker.assignmentName;
	    
	    input.setId(Integer.parseInt(studentId));
	    
	    if ((currentDate.equals(startDate) || currentDate.after(startDate)) &&
				(currentDate.equals(endDate) || currentDate.before(endDate))) 
		{
			// Local machine URL
			URL url = new URL("http://localhost:8080/server/inputs/" + studentId);
			// AWS URL
			//URL url = new URL("http://34.224.41.66:8080/server/inputs/" + studentId);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			
			Gson gson = new Gson();
		    String inputJson = gson.toJson(input);
		    
		    OutputStream os = conn.getOutputStream();
			os.write(inputJson.getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			}
			conn.disconnect();
		}
	}
}
