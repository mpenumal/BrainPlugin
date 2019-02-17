package com.asu.tutorcompanion.brainplugin.custom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.ResourcesPlugin;
import com.asu.tutorcompanion.brainplugin.splashHandlers.InteractiveSplashHandler;
import com.google.gson.Gson;

public class Client {
	
	/**
	 * Get Assignments using API 
	 * @throws IOException
	 * @throws ParseException 
	 * @throws JSONException 
	 */
	public void getAssignmentAPI() throws IOException, ParseException {
		String downloadFolder = Constants.DOWNLOAD_FOLDER_NAME;
		String directoryPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + 
				File.separator + downloadFolder;
		
		// Local machine URL
		URL url = new URL("http://localhost:8080/server/assignments");
		// Manohar AWS URL
//		URL url = new URL("http://34.224.41.66:8080/assignments");

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
		
		String fileName = conn.getHeaderField("Content-Disposition").split("=")[1].replace("\"", "");
		File file = new File(directoryPath + File.separator + fileName);
			
		if (!file.exists() || file.isDirectory()) {
			FileUtils.copyURLToFile(url, file);
		}
		else {
			System.out.println("File "+ fileName +" Already Exists. So did not replace.");
		}
		
		conn.disconnect();
	}
	
	/** 
	 * Send captured events to API
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public void sendInput(InputModel input) throws IOException, ParseException {
		
		String studentId = Constants.DEFAULT_STUDENT_ID;
		
		// Comment when using locally
		studentId = InteractiveSplashHandler.login_userName;
	    input.setId(Integer.parseInt(studentId));
	    
	    // Local machine URL
	    URL url = new URL("http://localhost:8080/server/inputs/");
	    // AWS URL
	    //URL url = new URL("http://34.224.41.66:8080/server/inputs/");
	 			
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
