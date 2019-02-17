package com.asu.tutorcompanion.brainplugin.views;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.ViewPart;

import com.asu.tutorcompanion.brainplugin.custom.BrainService;
import com.asu.tutorcompanion.brainplugin.custom.Client;
import com.asu.tutorcompanion.brainplugin.custom.ManageProject;
import com.asu.tutorcompanion.brainplugin.custom.Constants;
import com.asu.tutorcompanion.brainplugin.views.AssignmentQuestionsView.ViewLabelProvider;

public class HelpView extends ViewPart {
	
	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.asu.tutorcompanion.brainplugin.views.HelpView";
	private static String messageLabel = "This is the help view.";
	private String brainMessage = "";
	private int brainCode = 0;	
	private TableViewer viewer;
	private Action helpAction;
	private Action doubleClickAction;
	
	
	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}
		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}
		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	/**
	 * The constructor.
	 */
	public HelpView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	@Override
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setInput(new String[] { messageLabel });
		viewer.setLabelProvider(new ViewLabelProvider());
		
		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "com.asu.tutorcompanion.brainplugin.helpViewer");
		getSite().setSelectionProvider(viewer);
		hookContextMenu();
		makeActions();
		hookDoubleClickAction();
		contributeToActionBars();
	}
	
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				HelpView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}
	
	private void fillContextMenu(IMenuManager manager) {
		manager.add(helpAction);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void makeActions() {
		helpAction = new Action() {
			public void run() {
				if (viewer != null) {
					// clear values before new request
					brainMessage = "";
					brainCode = 0;
					BrainService brainService = new BrainService();
					try {
						// TODO: brainService.getMessage();
						brainMessage = "Message from Brain";
						brainCode = 12;
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					ArrayList<String> arrList = createHelpResponse();
					viewer.setContentProvider(ArrayContentProvider.getInstance());
					viewer.setInput(arrList);
					viewer.setLabelProvider(new ViewLabelProvider());
				}
				else {
					showMessage("Help Action executed");
				}
				viewer.setLabelProvider(new ViewLabelProvider());
			}
		};
		helpAction.setText("Help Action");
		helpAction.setToolTipText("Help Action tooltip");
		helpAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
		getImageDescriptor(ISharedImages.IMG_LCL_LINKTO_HELP));
		
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				String listItemName = obj.toString(); 
				ArrayList<String> viewerList = (ArrayList<String>) viewer.getInput();
				
				if (viewerList.contains(Constants.FEEDBACK_1)) {
					if (listItemName.equals(Constants.FEEDBACK_1) || listItemName.equals(Constants.FEEDBACK_2) ||
							listItemName.equals(Constants.FEEDBACK_3) || listItemName.equals(Constants.FEEDBACK_4)) {
						// TODO: BrainService save feedback method
						ArrayList<String> arrList = createFeedbackResponse();
						viewer.setContentProvider(ArrayContentProvider.getInstance());
						viewer.setInput(arrList);
						viewer.setLabelProvider(new ViewLabelProvider());
					} else {
						ArrayList<String> arrList = new ArrayList<String>();
						arrList.add(Constants.FEEDBACK_MISSED);
						arrList.addAll(createHelpResponse());
						viewer.setContentProvider(ArrayContentProvider.getInstance());
						viewer.setInput(arrList);
						viewer.setLabelProvider(new ViewLabelProvider());
					}
				}
			}		
		};
	}
	
	private ArrayList<String> createHelpResponse() {
		ArrayList<String> arrList = new ArrayList<String>();
		arrList.add("code = " + brainCode);
		arrList.add("message = " + brainMessage);
		arrList.add("");
		arrList.add(Constants.FEEDBACK_REQUEST);
		arrList.add(Constants.FEEDBACK_4);
		arrList.add(Constants.FEEDBACK_3);
		arrList.add(Constants.FEEDBACK_2);
		arrList.add(Constants.FEEDBACK_1);
		return arrList;
	}
	
	private ArrayList<String> createFeedbackResponse() {
		ArrayList<String> arrList = new ArrayList<String>();
		arrList.add("code = " + brainCode);
		arrList.add("message = " + brainMessage);
		arrList.add("");
		arrList.add(Constants.FEEDBACK_RESPONSE);
		return arrList;
	}
	
	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			"Help View",
			message);
	}
	
	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}
	
	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}
	
	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(helpAction);
		manager.add(new Separator());
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(helpAction);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

}
