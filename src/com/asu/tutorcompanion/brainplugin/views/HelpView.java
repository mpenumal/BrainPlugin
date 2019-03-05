package com.asu.tutorcompanion.brainplugin.views;

import java.io.IOException;
import java.util.ArrayList;

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
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.asu.tutorcompanion.brainplugin.Activator;
import com.asu.tutorcompanion.brainplugin.custom.Client;
import com.asu.tutorcompanion.brainplugin.custom.Constants;
import com.asu.tutorcompanion.brainplugin.custom.InputModel;
import com.asu.tutorcompanion.brainplugin.custom.ManageListeners;

public class HelpView extends ViewPart implements ISelectionListener {
	
	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.asu.tutorcompanion.brainplugin.views.HelpView";
	private static String messageLabel = "This is the help view.";
	private TableViewer viewer;
	private Action helpAction;
	private Action doubleClickAction;
	private static boolean feedbackNeeded = false;
	private static boolean defaultResponseNeeded = true;
	
	
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
		getSite().getPage().addSelectionListener(this);
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
					try {
						ManageListeners manageListeners = new ManageListeners();
						InputModel input = manageListeners.helpRequest();
						setBrainValues(input);
						feedbackNeeded = true;
						createHelpResponse(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
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
				
				@SuppressWarnings("unchecked")
				ArrayList<String> viewerList = (ArrayList<String>) viewer.getInput();
				
				if (viewerList.contains(Constants.FEEDBACK_1)) {
					if (listItemName.equals(Constants.FEEDBACK_1) || listItemName.equals(Constants.FEEDBACK_2) ||
							listItemName.equals(Constants.FEEDBACK_3) || listItemName.equals(Constants.FEEDBACK_4)) {
						Client client = new Client();
						InputModel input = new InputModel();
						input.setId(Activator.getDefault().getInputId());
						input.setMessageCode(Activator.getDefault().getBrainCode());
						input.setMessageGiven(Activator.getDefault().getBrainMessage());
						feedbackNeeded = false;
						
						switch(listItemName)
						{
							case Constants.FEEDBACK_4:
								input.setFeedbackSurvey(4);
								break;
							case Constants.FEEDBACK_3:
								input.setFeedbackSurvey(3);
								break;
							case Constants.FEEDBACK_2:
								input.setFeedbackSurvey(2);
								break;
							case Constants.FEEDBACK_1:
								input.setFeedbackSurvey(1);
								break;
							default:
								input.setFeedbackSurvey(0);
						}
						
						try {
							client.updateInput(input);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						createFeedbackResponse();
					} else {
						createHelpResponse(true);
					}
				}
			}		
		};
	}
	
	public static void setBrainValues(InputModel input) {
		Activator.getDefault().setInputId(input.getId());
		Activator.getDefault().setBrainCode(input.getMessageCode());
		Activator.getDefault().setBrainMessage(input.getMessageGiven());
	}
	
	private void clearBrainValues() {
		// clear values before new request
		Activator.getDefault().setInputId(0);
		Activator.getDefault().setBrainCode(0);
		Activator.getDefault().setBrainMessage("");
	}
	
	private void createHelpResponse(boolean feedbackMissed) {
		ArrayList<String> arrList = new ArrayList<String>();
		// Displays the corresponding code for the message given to the students
		//arrList.add("code = " + Activator.getDefault().getBrainCode());
		arrList.add("message = " + Activator.getDefault().getBrainMessage());
		arrList.add("");
		if (feedbackMissed) {
			arrList.add(Constants.FEEDBACK_MISSED);
		}
		arrList.add("");
		arrList.add(Constants.FEEDBACK_REQUEST);
		arrList.add(Constants.FEEDBACK_4);
		arrList.add(Constants.FEEDBACK_3);
		arrList.add(Constants.FEEDBACK_2);
		arrList.add(Constants.FEEDBACK_1);
		
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setInput(arrList);
		viewer.setLabelProvider(new ViewLabelProvider());
	}
	
	private void createDefaultResponse() {
		ArrayList<String> arrList = new ArrayList<String>();
		// Displays the corresponding code for the message given to the students
		//arrList.add("code = " + Activator.getDefault().getBrainCode());
		//arrList.add("message = " + Activator.getDefault().getBrainMessage());
		arrList.add("Please begin your assignment.");
		
		//arrList.add(Constants.FEEDBACK_REQUEST);
		//arrList.add(Constants.FEEDBACK_4);
		//arrList.add(Constants.FEEDBACK_3);
		//arrList.add(Constants.FEEDBACK_2);
		//arrList.add(Constants.FEEDBACK_1);
		
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setInput(arrList);
		viewer.setLabelProvider(new ViewLabelProvider());
	}
	
	private void createFeedbackResponse() {
		ArrayList<String> arrList = new ArrayList<String>();
		//arrList.add("code = " + Activator.getDefault().getBrainCode());
		//arrList.add("message = " + Activator.getDefault().getBrainMessage());
		arrList.add("");
		arrList.add(Constants.FEEDBACK_RESPONSE);
		
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setInput(arrList);
		viewer.setLabelProvider(new ViewLabelProvider());
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
	
	public static boolean isFeedbackNeeded() {
		return feedbackNeeded;
	}

	public static void setFeedbackNeeded(boolean feedbackNeeded) {
		HelpView.feedbackNeeded = feedbackNeeded;
	}

	public static boolean isDefaultResponseNeeded() {
		return defaultResponseNeeded;
	}

	public static void setDefaultResponseNeeded(boolean defaultResponseNeeded) {
		HelpView.defaultResponseNeeded = defaultResponseNeeded;
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (defaultResponseNeeded) {
			createDefaultResponse();
		}
		else if (!this.feedbackNeeded) {
			createFeedbackResponse();
		}
		else if (this.feedbackNeeded) {
			createHelpResponse(true);
		}
	}
}
