package org.java.app.common;

public class Constants {
	
	public static class SERVER_RESPONSE {
		public static String SUCCESS = "success";
		public static String FAIL = "fail";
		public static String LOGOUT = "logout";
	}
	
	public static class SESSION_RESPONSE {
		public static String LOGOUT = "logout";
	}
	
	public static class USER_DB_TABLES{
	    public static String USER_DATA = "userdata";
	    public static String USER_TABLE_MAPPER = "usertablemap";
	    public static String STUDENT_ASSIGNMENT_TABLE_MAPPER = "studentassignmentmap";
	}
	
	public static class USER_DATA_COLUMNS{// DB TAble: USER_DATA
		public static String USERNAME = "UserName";
		public static String PASSWORD = "Password";
		public static String UIN = "UIN";
		public static String USER_ROLE = "UserRole";
		public static String FIRSTNAME = "FirstName";
		public static String LASTNAME = "LastName";
		public static String EMAILID = "EmailID";
	}
	
	public static class USER_TABLE_COLUMNS{ // DB Table: USERNAME_TABLENAME_TABLETYPE, USER_TABLE_MAPPER
	   public static String USERNAME = "UserName";
	   public static String TABLENAME = "TableName";
	   public static String TABLETYPE = "TableType"; // ASX,IDX,RSX
	}
	
	public static class STUDENT_ASSIGNMENT_TABLE_COLUMNS{ // DB Table: STUDENT_ASSIGNMENT_TABLE_MAPPER
		public static String USERNAME = "UserName";
		public static String ASSIGNMENT_NAME = "AssignmentName"; // ASSIGNMENTNAME
		public static String IS_ACTIVE = "IsActive";
		public static String SCORE = "Score";
	}
	
	public static class ASSIGNMENT_TABLE_COLUMNS{ // DB Table: USERNAME_ASSIGNMENTNAME_TABLETYPE
		public static String QUESTION_ID = "QuestionID";
		public static String QUESTION_DESC = "QuestionDesc";
		public static String POINTS = "Points";
		public static String SOLUTION = "Solution";
	}

}
