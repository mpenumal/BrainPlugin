package com.asu.tutorcompanion.brainplugin.custom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.LibraryLocation;

public class ManageProject {
	
	/**
	 * Create new java project based on the assignment
	 * @return
	 * @throws CoreException 
	 * @throws IOException 
	 */
	public void createJavaProject(String fileName, String packageName, String className) throws CoreException, IOException {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = root.getProject(Constants.PROJECT_NAME);
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
	 * Get the current code file, which is the assignment java file that student is working on
	 * extract student's answer function from the file
	 * @return
	 * @throws CoreException
	 * @throws IOException 
	 */
	public List<String> getStudentMethodFromFile() throws CoreException, IOException {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = root.getProject(Constants.PROJECT_NAME);
		IPath path = new Path(File.separator + "src" + File.separator + Constants.PACKAGE_NAME + File.separator  + Constants.ASSIGNMENT_NAME);
		File file = project.getFile(path).getLocation().toFile();
		List<String> allCode = Files.readAllLines(file.toPath());
		List<String> method = new ArrayList<String>();
		String studentID = "";
		String courseName = "";
		
		if (allCode != null && !allCode.isEmpty()) {
			int startIndex = 0, endIndex = 0;
			for (int i = 0; i < allCode.size(); i++) {
				if (allCode.get(i).contains(Constants.STUDENT_FUNCTION_START)) {
					startIndex = i;
				}
				if (startIndex > 0 && endIndex == 0) {
					method.add(allCode.get(i));
				}
				if (allCode.get(i).contains(Constants.STUDENT_FUNCTION_END)) {
					endIndex = i;
					break;
				}
				if (allCode.get(i).contains(Constants.STUDENT_ID_ENTRY)) {
					studentID = allCode.get(i).split(Constants.STUDENT_ID_ENTRY)[1];
				}
				if (allCode.get(i).contains(Constants.STUDENT_COURSE_ENTRY)) {
					courseName = allCode.get(i).split(Constants.STUDENT_COURSE_ENTRY)[1];
				}
			}
			
		}
		method.add(studentID);
		method.add(courseName);
		System.out.println("Student ID: " + studentID);
		System.out.println("Course: " + courseName);

		
		return method;
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
