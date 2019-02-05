package com.asu.tutorcompanion.brainplugin.custom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.LibraryLocation;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;

public class ManageProject {
	/**
	 * @return
	 * @throws CoreException 
	 * @throws IOException 
	 */
	public void createJavaProject(String projectName, String fileName, String packageName, String className) throws CoreException, IOException {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = root.getProject(projectName);
		if (!project.exists() ) {
			project.create(null);
			project.open(null);
			
			IProjectDescription description = project.getDescription();
			description.setNatureIds(new String[] { JavaCore.NATURE_ID });
			project.setDescription(description, null);
			
			IJavaProject javaProject = JavaCore.create(project);
			
			IFolder binFolder = project.getFolder("bin");
			binFolder.create(false, true, null);
			javaProject.setOutputLocation(binFolder.getFullPath(), null);
			
			List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
			IVMInstall vmInstall = JavaRuntime.getDefaultVMInstall();
			LibraryLocation[] locations = JavaRuntime.getLibraryLocations(vmInstall);
			for (LibraryLocation element : locations) {
			 entries.add(JavaCore.newLibraryEntry(element.getSystemLibraryPath(), null, null));
			}
			//add libs to project class path
			javaProject.setRawClasspath(entries.toArray(new IClasspathEntry[entries.size()]), null);
			
			IFolder sourceFolder = project.getFolder("src");
			sourceFolder.create(false, true, null);
			
			IPackageFragmentRoot fragmentRoot = javaProject.getPackageFragmentRoot(sourceFolder);
			IClasspathEntry[] oldEntries = javaProject.getRawClasspath();
			IClasspathEntry[] newEntries = new IClasspathEntry[oldEntries.length + 1];
			System.arraycopy(oldEntries, 0, newEntries, 0, oldEntries.length);
			newEntries[oldEntries.length] = JavaCore.newSourceEntry(fragmentRoot.getPath());
			javaProject.setRawClasspath(newEntries, null);
			
			IPackageFragment pack = javaProject.getPackageFragmentRoot(sourceFolder).createPackageFragment(packageName, false, null);
			
			String source;
			source = readFile(fileName);
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("package " + pack.getElementName() + ";\n");
			buffer.append("\n");
			buffer.append(source);
			
			@SuppressWarnings("unused")
			ICompilationUnit cu = pack.createCompilationUnit(className , buffer.toString(), false, null);
		}
		else {
			if (!project.isOpen()) {
				project.open(null);
			}
		}
	}
	
	/**
	  * Get the current project from the selectionService
	  * @return projectName
	  */
	public IProject getCurrentSelectedProject() {
		IProject project = null;
		 ISelectionService selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
		 ISelection selection = selectionService.getSelection();
		 
		 if(selection instanceof IStructuredSelection) {
			 Object element = ((IStructuredSelection)selection).getFirstElement();
			 
			 if (element instanceof IResource) {
				 project= ((IResource)element).getProject();
			 } 
			 else if (element instanceof IPackageFragmentRoot) {
				 IJavaProject jProject = ((IPackageFragmentRoot)element).getJavaProject();
				 project = jProject.getProject();
		     } else if (element instanceof IJavaElement) {
		    	 IJavaProject jProject= ((IJavaElement)element).getJavaProject();
		    	 project = jProject.getProject();
		     }
		 }
		return project;
	}
	
	public void saveJavaCode(String projectName, String fileName, String packageName, String className) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = root.getProject(projectName);
	}
	
	private String readFile(String fileName) throws IOException {
		String file = FileSystems.getDefault().getPath(fileName).normalize().toAbsolutePath().toString();
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
}
