package com.asu.tutorcompanion.brainplugin.launching;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.eclipse.debug.ui.console.IConsole;
import org.eclipse.debug.ui.console.IConsoleLineTracker;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;

import com.asu.tutorcompanion.brainplugin.model.Input;
import com.asu.tutorcompanion.brainplugin.custom.Client;

public class TutorPluginLogTracker implements IConsoleLineTracker {

	private IConsole m_console;
	public static String assignmentName;
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void init(IConsole console) {
		m_console = console;
		
	}

	@Override
	public void lineAppended(IRegion region) {
            try {
            	String line = m_console.getDocument().get(region.getOffset(), region.getLength());
            	line = line.trim();
            	
            	Input input = new Input();
            	
            	if (!line.equals("")) {
            		List<String> lines = Collections.singletonList(line.trim()); 
            		
            		input.setAssignmentCompletedSuccessfully(lines.get(0).equalsIgnoreCase("Success!") ? 1 : 0);
                	
            		Client client = new Client();
//				    svc.sendLogClient(lines);
	            	Input input2 = new Input();
		    		 input2.setNumberRunAttempts(2);
		    		 client.sendInput(input2);
            	}
				
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
