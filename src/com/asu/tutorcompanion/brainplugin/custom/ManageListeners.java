package com.asu.tutorcompanion.brainplugin.custom;

import org.eclipse.jface.text.IRegion;

// TODO:
public class ManageListeners {
	// on other Actions: GetFile -> CreateInputModel (using already saved copy) -> SendInput
	
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
	
	// on RUN: GetFile -> CreateInputModel (using already saved copy) -> SaveFile -> SendInput
	// This event listener deals with ERRORS, RUN and DEBUG
	public void runListener_PostExecuteSuccess() {
		
	}
}
