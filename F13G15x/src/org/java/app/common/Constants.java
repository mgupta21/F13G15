package org.java.app.common;

public class Constants {
	
	public static class SERVER_RESPONSE {
		public static String SUCCESS = "success";
		public static String FAIL = "fail";
		public static String LOGOUT = "logout";
		public static String ERROR = "Error";
		public static String CORRUPT = "Corrupt";
	}
	
	public static class SESSION_RESPONSE {
		public static String LOGOUT = "logout";
	}
	
	public static class USER_DB_TABLES{
	    public static String USER_DATA = "userdata";
	    public static String USER_TABLE_MAPPER = "usertablemap";
	    public static String PROFESSOR_ROSTER_MAP = "professorrostermap";
	    public static String STUDENT_ASSIGNMENT_TABLE_MAPPER = "studentassignmentmap";
	    public static String SOURCE_TABLE_AND_ASSIGNMENT_MAPPER = "tableandassignmentmap";
	}
	
	public static class USER_DATA_COLUMNS{// DB TAble: USER_DATA
		public static String USERNAME = "UserName";
		public static String PASSWORD = "Password";
		public static String UIN = "UIN";
		public static String USER_ROLE = "UserRole";
		public static String FIRSTNAME = "FirstName";
		public static String LASTNAME = "LastName";
		public static String EMAILID = "EmailID";
		public static String COURSE = "Course";
	}
	
	public static class USER_TABLE_COLUMNS{ // DB Table: USERNAME_TABLENAME_TABLETYPE, USER_TABLE_MAPPER
	   public static String USERNAME = "UserName";
	   public static String TABLENAME = "TableName";
	   public static String TABLETYPE = "TableType"; // ASX,IDX,RSX
	}
	
	public static class PROFESSOR_ROSTER_COLUMNS{
		   public static String PROFESSORNAME = "ProfessorName";
		   public static String COURSE = "Course";
	}
	
	public static class STUDENT_ASSIGNMENT_TABLE_COLUMNS{ // DB Table: STUDENT_ASSIGNMENT_TABLE_MAPPER //id AutoIncrement
		public static String USERNAME = "UserName";
		public static String ASSIGNMENT_NAME = "AssignmentName"; // ASSIGNMENTNAME
		public static String COURSE = "Course";
		public static String IS_ACTIVE = "IsActive";
		public static String SCORE = "Score";
	}
	
	public static class ASSIGNMENT_TABLE_COLUMNS{ // DB Table: USERNAME_ASSIGNMENTNAME_TABLETYPE
		public static String QUESTION_ID = "QuestionID";
		public static String QUESTION_DESC = "QuestionDesc";
		public static String QUESTION_COLUMN_NAME = "QuesColumn";
		public static String ANALYSIS_TYPE = "AnalysisType";
		public static String POINT = "Point";
		public static String SOLUTION = "Solution";
	}
	
	public static class SOURCE_TABLE_AND_ASSIGNMENT_COLUMNS{ //id AutoIncrement
		public static String ASSIGNMENT_NAME = "AssignmentName";
		public static String PROFESSOR_NAME = "ProfessorName";
		public static String COURSE = "Course";
		public static String SOURCE_TABLENAME = "SourceTable";
	}
	
	public static class TABLE_TYPES{ 
		public static String ROOSTER = "rsx";
		public static String ASSIGNMENT_TABLE = "asx";
		public static String INDIVIDUAL = "idx";
		public static String ASSIGNMENT = "aaa";
	}
	
	public static class ROSTER_COLUMNS{
		public static String LASTNAME = "Last Name";
		public static String FIRSTNAME = "First Name";
		public static String USERNAME = "Username";
		public static String UIN = "Student ID";
		public static String GRADE = "Grade";
		public static String TOTAL = "Total";
		
		public static String HW01 = "HW01";
		public static String Quiz01 = "Quiz01";
		public static String Exam01 = "Exam#1";
		
		public static String HW02 = "HW02";
		public static String Quiz02 = "Quiz02";
		public static String Exam02 = "Exam#2";
		
		public static String HW03 = "HW03";
		public static String Quiz03 = "Quiz03";
		public static String Exam03 = "Exam#3";
		
		public static String HW04 = "HW04";
		public static String Quiz04 = "Quiz04";
		public static String Exam04 = "Exam#4";
				
	}
	
	public static String INDEX_COLUMN_NAME = "ca8d6634-e64b-4792-aa70-e6bb2f586a22";

}
