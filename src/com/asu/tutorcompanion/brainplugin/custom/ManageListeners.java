package com.asu.tutorcompanion.brainplugin.custom;

import org.eclipse.jface.text.IRegion;

// TODO:
public class ManageListeners {
	// on RUN: GetFile -> CreateInputModel -> SaveFile -> SendInput
	// on other Actions: GetFile -> CreateInputModel -> SendInput
	
	public void logTracker_LineAppended(IRegion region) {
		
	}
	
	// no logging required.
	// Use it to set timers.
	public void activator_EarlyStartup() {
		
	}
	
	// no logging required.
	// Use it to set timers.
	public void activator_stop() {
		
	}
}
