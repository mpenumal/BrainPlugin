package com.asu.tutorcompanion.brainplugin.custom;

public final class Constants {
	private Constants() {
        // restrict instantiation
	}
	public static final String DEFAULT_COURSE_NAME = "ASUCSE";
	public static final String DEFAULT_STUDENT_ID = "1111111111";
	public static final String DOWNLOAD_FOLDER_NAME = "AssignmentList_Cosmo_Client";
	public static final String PACKAGE_NAME = "assignmentPackage";
	public static final String ASSIGNMENT_NAME = "Recursion";
	public static final String FILE_TYPE = ".java";
	public static final String ASSIGNMENT_STARTDATE = "2019/02/03 00:00:00";
	public static final String ASSIGNMENT_ENDDATE = "2019/02/24 00:00:00";
	
    public static final String ASSIGNMENT = "public class Recursion {\r\n" + 
											"	public static void main(String[] args) {\r\n" + 
											"        System.out.println(\"Success!\");\r\n" + 
											"   }" + 
											"	\r\n" + 
											"//	For the following problem, you may not use FOR, WHILE, DO, or any other looping construct. \r\n" + 
											"//	Write static separate methods to Compute the length of an IntList.\r\n" + 
											"\r\n" + 
											"}\r\n" + 
											"";
}
