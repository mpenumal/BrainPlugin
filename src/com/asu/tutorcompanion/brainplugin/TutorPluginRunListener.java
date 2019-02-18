package com.asu.tutorcompanion.brainplugin;

import java.io.IOException;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.json.JSONException;

import com.asu.tutorcompanion.brainplugin.views.HelpView;
import com.asu.tutorcompanion.brainplugin.custom.InputModel;
import com.asu.tutorcompanion.brainplugin.custom.ManageListeners;

/**
 * 
 * @source https://github.com/wakatime/eclipse-wakatime
 *
 */
public class TutorPluginRunListener implements IExecutionListener {
	
	 @Override
	 public void notHandled(String commandId, NotHandledException exception) {
	        // TODO Auto-generated method stub
	 }
	 @Override
	 public void postExecuteFailure(String commandId,
	            ExecutionException exception) {
	        // TODO Auto-generated method stub
	 }
	 @Override
	 public void postExecuteSuccess(String commandId, Object returnValue) {
		 if (commandId.equals("org.eclipse.jdt.debug.ui.localJavaShortcut.run") ||
				 commandId.equals("org.eclipse.debug.ui.commands.RunLast")) {
			 
	         IWorkbench workbench = PlatformUI.getWorkbench();
	         if (workbench == null) return;
	         IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
	         if (window == null) return;
	         if (window.getPartService() == null) return;
	         if (window.getPartService().getActivePart() == null) return;
	         if (window.getPartService().getActivePart().getSite() == null) return;
	         if (window.getPartService().getActivePart().getSite().getPage() == null) return;
	         

	        IWorkbenchPage page = window.getActivePage();
	        ManageListeners manageListeners = new ManageListeners();
    		 try {
    			 InputModel input = manageListeners.runRequest();
    			 HelpView.setBrainValues(input);
    			 page.showView("com.asu.tutorcompanion.brainplugin.views.HelpView");
			} catch (CoreException | IOException | JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				getCompilationErrorsFromProblemsView(page);
			} catch (CoreException | IOException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        }
		else if (commandId.equals("org.eclipse.jdt.debug.ui.localJavaShortcut.debug") ||
				 commandId.equals("org.eclipse.debug.ui.commands.DebugLast")) {
			
			 IWorkbench workbench = PlatformUI.getWorkbench();
	         if (workbench == null) return;
	         IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
	         if (window == null) return;
	         if (window.getPartService() == null) return;
	         if (window.getPartService().getActivePart() == null) return;
	         if (window.getPartService().getActivePart().getSite() == null) return;
	         if (window.getPartService().getActivePart().getSite().getPage() == null) return;
    		 
	         IWorkbenchPage page = window.getActivePage();
	         ManageListeners manageListeners = new ManageListeners();
    		 try {
				InputModel input = manageListeners.debugRequest();
				HelpView.setBrainValues(input);
				page.showView("com.asu.tutorcompanion.brainplugin.views.HelpView");
			} catch (CoreException | IOException | JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				getCompilationErrorsFromProblemsView(page);
			} catch (CoreException | IOException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 }
	 
	 /**
	 * Get the compilation errors from the Problems View
	 * @throws CoreException
	 * @throws ParseException 
	 * @throws JSONException 
	 * @throws IOException 
	 */
	private void getCompilationErrorsFromProblemsView(IWorkbenchPage page) throws CoreException, IOException, JSONException {
		IMarker[] markers = ResourcesPlugin.getWorkspace().getRoot().findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
		if (markers != null && markers.length > 0) {
			ManageListeners manageListeners = new ManageListeners();
			// Getting the first listed compilation error
			InputModel input = manageListeners.errorCaptureRequest((String) markers[0].getAttribute(IMarker.MESSAGE));
			HelpView.setBrainValues(input);
			page.showView("com.asu.tutorcompanion.brainplugin.views.HelpView");
		}
	}
	 
	 @Override
	 public void preExecute(String commandId, ExecutionEvent event) {
		 // TODO Auto-generated method stub
	 }
}
