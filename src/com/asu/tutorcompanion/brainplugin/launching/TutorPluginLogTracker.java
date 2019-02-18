package com.asu.tutorcompanion.brainplugin.launching;

import java.util.Collections;
import java.util.List;

import org.eclipse.debug.ui.console.IConsole;
import org.eclipse.debug.ui.console.IConsoleLineTracker;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;

import com.asu.tutorcompanion.brainplugin.custom.Constants;
import com.asu.tutorcompanion.brainplugin.custom.InputModel;
import com.asu.tutorcompanion.brainplugin.custom.ManageListeners;
import com.asu.tutorcompanion.brainplugin.views.HelpView;

public class TutorPluginLogTracker implements IConsoleLineTracker {

	private IConsole m_console;
	
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
            	
            	if (!line.equals("")) {
            		List<String> lines = Collections.singletonList(line.trim());
            		if (lines.get(0).equalsIgnoreCase(Constants.STUDENT_FUNCTION_SUCCESS)) {
            			// SUBMIT Successfully
            			ManageListeners manageListeners = new ManageListeners();
            			InputModel input = manageListeners.submitRequest();
            			HelpView.setBrainValues(input);
//            			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart().getSite().getPage().showView("com.asu.tutorcompanion.brainplugin.views.HelpView");
            		} else if (lines.get(0).startsWith("Exception")) {
            			ManageListeners manageListeners = new ManageListeners();
            			// RUNTIME ERRORS call
            			InputModel input = manageListeners.errorCaptureRequest(lines.get(0));
            			HelpView.setBrainValues(input);
//            			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart().getSite().getPage().showView("com.asu.tutorcompanion.brainplugin.views.HelpView");
            		}
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
