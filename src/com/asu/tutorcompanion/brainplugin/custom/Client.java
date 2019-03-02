package com.asu.tutorcompanion.brainplugin.custom;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.ResourcesPlugin;
import com.google.gson.Gson;

public class Client {
	
	/**
	 * Get Assignments using API 
	 * @throws IOException
	 */
	public void getAssignmentAPI() throws IOException {
		String downloadFolder = Constants.DOWNLOAD_FOLDER_NAME;
		String directoryPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + 
				File.separator + downloadFolder;
		
		// Local machine URL
		URL url = new URL("http://localhost:8080/server/assignments");
		// Manohar AWS URL
		// URL url = new URL("http://34.224.41.66:8080/assignments");

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
	 * Save new events to API
	 * @param input
	 * @throws IOException
	 */
	public Long saveInput(InputModel input) throws IOException {
		
	    
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

	 	BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	 	StringBuilder sb = new StringBuilder();
	 	String output;
	 	Long id = null;
	 	while ((output = br.readLine()) != null && (id == null || id == 0)) {
	 	// Checks if input object is JSONObject or is already an int
	 		if (output.startsWith("{")) {
	 			int idStartIndex = output.indexOf(":");
		 		int idEndIndex = output.indexOf(",");
		 		String outputId = output.substring(idStartIndex + 1, idEndIndex);
		 		id = Long.parseLong(outputId);
	 		}
	 		else {
	 			id = Long.parseLong(output);
		 		sb.append(output);
	 		}
	 	}

	 	conn.disconnect();
	 	return id;
	}
	
	/**
	 * Update existing events with Brain message, code and Feedback
	 * @param input
	 * @throws IOException 
	 */
	public void updateInput(InputModel input) throws IOException {	    
	    // Local machine URL
	    URL url = new URL("http://localhost:8080/server/inputs/" + input.getId());
	    // AWS URL
	    //URL url = new URL("http://34.224.41.66:8080/server/inputs/");
	 			
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setDoOutput(true);
	    conn.setRequestMethod("PUT");
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
