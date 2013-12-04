package org.java.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.java.app.common.Constants;
import org.java.app.exceptions.DuplicateTableException;
import org.java.app.exceptions.DuplicateUserException;
import org.java.app.exceptions.InsertionException;
import org.java.app.interfaces.IDatabaseService;
import org.java.app.model.Assignment;
import org.java.app.model.AssignmentTable;
import org.java.app.model.ColumnDataType;
import org.java.app.model.Question;
import org.java.app.model.RosterTable;
import org.java.app.model.SolvedQuestion;
import org.java.app.model.TableColumn;
import org.java.app.model.UserDataAccess;
import org.java.app.model.UserProfile;
import org.java.app.model.UserRole;
import org.java.app.model.UserTable;

@ManagedBean(name = "dbService")
@ApplicationScoped
public class DatabaseServiceImpl implements IDatabaseService {
	
	private String serverAdd;
	private String schema;
	private String user;
	private String password;
	private Connection conn;
	
	public DatabaseServiceImpl() throws Exception {
		// TODO : Define this in XML
		this.serverAdd = "localhost";
		this.schema = "f13g15";
		this.user = "root";
		this.password = "admin";
		connect(serverAdd, schema, user, password);
	}
	
	//@Override
	public void connect(String serverAdd, String schema, String user,
			String password) {

			try {

				String url = "jdbc:mysql://" + serverAdd + "/" + schema;

				Class.forName("com.mysql.jdbc.Driver").newInstance();

				conn = DriverManager.getConnection(url, user, password);

			} catch (InstantiationException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
	}
	
	public boolean isExistingUser(HashMap<String, Object> map) {

		ResultSet rs = null;
		
		try {

			Statement stmt = conn.createStatement();

			String query = getQuery(Constants.USER_DB_TABLES.USER_DATA, map);

		    rs = stmt.executeQuery(query);

			if(rs.next()){
				return true;
			}else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		finally
		{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void createNewUser(HashMap<String, Object> userData) {
		
		try {
			Statement st = conn.createStatement();

			StringBuffer colNames = new StringBuffer();
			StringBuffer colValues = new StringBuffer();

			for (Iterator<Entry<String, Object>> iterator = userData.entrySet()
					.iterator(); iterator.hasNext();) {

				Entry<String, Object> entry = iterator.next();

				String colName = entry.getKey();
				Object val = entry.getValue();
				
				colNames.append(colName).append(",");

				if (val.getClass().isAssignableFrom(Integer.class)) {
					colValues.append(val).append(",");
				} else if (val.getClass().isAssignableFrom(String.class)) {
					colValues.append("'").append(val).append("',");
				} else if (val.getClass().isAssignableFrom(UserRole.class)) {
					colValues.append(((UserRole)val).getRoleID()).append(",");
				}else{
					colValues.append(val).append(",");
				}
			}
			
		    String a = colNames.substring(0, colNames.lastIndexOf(","));
			String b = colValues.substring(0,colValues.lastIndexOf(","));
			
			StringBuffer query = new StringBuffer();
			
			query.append("Insert into " + Constants.USER_DB_TABLES.USER_DATA);
			query.append(" (" + a + ") ");
			query.append("values");
			query.append(" (" + b + ");");
			String qry = query.toString();
			st.executeUpdate(qry);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private String getQuery(String tableName, HashMap<String, Object> map) {
		
		StringBuffer queryString = new StringBuffer();

		for (Iterator<Entry<String, Object>> iterator = map.entrySet()
				.iterator(); iterator.hasNext();) {
			Entry<String, Object> entry = iterator.next();

			queryString.append(entry.getKey());
			queryString.append("=");
			
			Object value = entry.getValue();

			if (value.getClass().isAssignableFrom(Boolean.class)) {
				queryString.append(value);
			} else {
				queryString.append("'").append(value).append("'");
			}

			if (iterator.hasNext()) {
				queryString.append(" AND ");
			}
		}

		return "SELECT * FROM " + tableName + " WHERE "
				+ queryString.toString();
	}

	public List<UserTable> getUserTables(String userName, String tableType) {
		
		List<UserTable> usertables = new ArrayList<UserTable>();
		ResultSet rs = null;
		
		try {
			
			StringBuffer query = new StringBuffer();
			
			
			Statement stmt = conn.createStatement();
			
			query.append("Select * from " + Constants.USER_DB_TABLES.USER_TABLE_MAPPER).append(" where ")
			.append(Constants.USER_DATA_COLUMNS.USERNAME + " = ").append("'" + userName + "'");
			if(tableType!=null){
				query.append(" and " + Constants.USER_TABLE_COLUMNS.TABLETYPE + " = '" + tableType + "'");
			}
			
			rs = stmt.executeQuery(query.toString());
			
			while (rs.next()) {
				UserTable table= new UserTable();
				TableColumn tableColumn = new TableColumn();
				
				List<TableColumn> tableColumns = new ArrayList<TableColumn>();
					table.setUserName(rs.getString(Constants.USER_TABLE_COLUMNS.USERNAME));
					table.setTableName(rs.getString(Constants.USER_TABLE_COLUMNS.TABLENAME));
						table.setTableType(rs.getString(Constants.USER_TABLE_COLUMNS.TABLETYPE));
					
					StringBuffer qry = new StringBuffer();
					
					qry.append("select * from INFORMATION_SCHEMA.COLUMNS where TABLE_NAME=")
					.append("'" + table.getUserName() + "_" + table.getTableName() + "_" + table.getTableType() + "'");
					
					tableColumns = getTableColumn(qry);
					table.setTableColumns(tableColumns);
					usertables.add(table);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return usertables;
	}
	
	
	public List<TableColumn> getTableColumn(StringBuffer query) {
		ResultSet rs = null;
		List<TableColumn> columns = new ArrayList<TableColumn>();
		
		
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query.toString());
			
			while (rs.next()) {
				TableColumn column = new TableColumn();
				column.setColName(rs.getString("COLUMN_NAME"));
				column.setColDataType(getColumnDataType(rs
						.getString("DATA_TYPE")));
				columns.add(column);
			}
		}catch (SQLException e) {
					throw new RuntimeException(e);
		}finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return columns;
	}
	
	private ColumnDataType getColumnDataType(String dataType) {
		
		switch (dataType) {
		case "double":
			return ColumnDataType.Double;
		case "int":
			return ColumnDataType.Integer;
		case "varchar":
			return ColumnDataType.String;
		}
		return ColumnDataType.Undefined;
	}
	
	public List<TableColumn> getColumnsByTableName(String tableName) {
		
		StringBuffer query = new StringBuffer();
		
		query.append("select * from INFORMATION_SCHEMA.COLUMNS where TABLE_NAME=")
		.append("'" + tableName + "'");
		
		ResultSet rs = null;
		List<TableColumn> columns = new ArrayList<TableColumn>();
		
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query.toString());
			
			// Skip First Row; Created while uploading file 
			rs.next();
			
			while (rs.next()) {
				TableColumn column = new TableColumn();
				column.setColName(rs.getString("COLUMN_NAME"));
				column.setColDataType(getColumnDataType(rs
						.getString("DATA_TYPE")));
				if(column.getColDataType()==ColumnDataType.Double || column.getColDataType()==ColumnDataType.Integer){
					columns.add(column);
				}
				
			}
		}catch (SQLException e) {
					throw new RuntimeException(e);
		}finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return columns;
	}

	public UserProfile getUserProfile(UserDataAccess userData) {
		UserProfile userProfile = userData.getUserProfile();
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			Statement stmt = conn.createStatement();
			
			// For Admin Course is set Dynamically
			if(isRoleAdmin(userData.getUserLogin().getUserName())){
				query.append("select * from ").append(Constants.USER_DB_TABLES.USER_DATA).append(" where `")
				.append(Constants.USER_DATA_COLUMNS.USERNAME).append("`='").append(userData.getUserLogin().getUserName()).append("'");
			}else{
				query.append("select * from ").append(Constants.USER_DB_TABLES.USER_DATA).append(" where `")
				.append(Constants.USER_DATA_COLUMNS.USERNAME).append("`='").append(userData.getUserLogin().getUserName()).append("'")
				.append(" and `").append(Constants.USER_DATA_COLUMNS.COURSE).append("`='").append(userData.getUserProfile().getCourse()).append("';");
			}
			
			rs = stmt.executeQuery(query.toString());
			
			while (rs.next()) {
				userProfile.setFirstName(rs.getString(Constants.USER_DATA_COLUMNS.FIRSTNAME));
				userProfile.setLastName(rs.getString(Constants.USER_DATA_COLUMNS.LASTNAME));
				userProfile.setUIN(rs.getString(Constants.USER_DATA_COLUMNS.UIN));
				int role = rs.getInt(Constants.USER_DATA_COLUMNS.USER_ROLE);
				if(role == UserRole.STUDENT.getRoleID()){
					userProfile.setUserRole(UserRole.STUDENT);
				}else if(role == UserRole.PROF.getRoleID()){
					userProfile.setUserRole(UserRole.PROF);
				}else{
					userProfile.setUserRole(UserRole.ADMIN);
				}
				userProfile.setEmail(rs.getString(Constants.USER_DATA_COLUMNS.EMAILID));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userProfile;
	}
	
	public boolean isRoleAdmin(String userName){
		
	ResultSet rs = null;
	int role=0;	
		try {

			Statement stmt = conn.createStatement();
			StringBuffer query = new StringBuffer();
			query.append("select `").append(Constants.USER_DATA_COLUMNS.USER_ROLE).append("` from ").append(Constants.USER_DB_TABLES.USER_DATA).append(" where `")
			.append(Constants.USER_DATA_COLUMNS.USERNAME).append("`='").append(userName).append("'");
			
		    rs = stmt.executeQuery(query.toString());

		    while (rs.next()) {
		    	role = rs.getInt(Constants.USER_DATA_COLUMNS.USER_ROLE);
			}
		    
		    if(role==UserRole.ADMIN.getRoleID()){
		    	return true;
		    }else{
		    	return false;
		    }

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		finally
		{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void uploadAssignment(Assignment assignment, String userName) {
		
		String dbTableName = assignment.getAssignmentName() + "_" + userName + "_" + assignment.getAssignmentTable().getCourse();
		
		try {
			Statement st = conn.createStatement();

			StringBuffer createQuery = new StringBuffer();
			createQuery.append("CREATE TABLE `").append(dbTableName).append("` (" + "`QuestionID` INT NOT NULL,")
			.append("`QuesColumn` VARCHAR(45) NOT NULL,")
			.append("`AnalysisType` VARCHAR(45) NOT NULL,")
			.append("`QuestionDesc` VARCHAR(45) NOT NULL,")
			.append("`Point` INT NOT NULL,") 
			.append("`Solution` DOUBLE NOT NULL,") 
			.append("PRIMARY KEY (`" + Constants.ASSIGNMENT_TABLE_COLUMNS.QUESTION_ID + "`));"
			);
			
			String qry = createQuery.toString();
			
			st.execute(qry);
			
			StringBuffer insertQuery = new StringBuffer();
			for (Question ques: assignment.getQuestions()){
				insertQuery.append("INSERT INTO `" + dbTableName + "` (`QuestionID`, `QuesColumn`, `AnalysisType`, `QuestionDesc`, `Point`, `Solution`) VALUES ('");
				insertQuery.append(ques.getQuestionID()).append("', '").append(ques.getQuesColName()).append("', '").append(ques.getAnalysisType()).append("', '")
				.append("Calculate " + ques.getAnalysisType() + " on column " + ques.getQuesColName()).append("', '")
				.append(ques.getPoint()).append("', '").append(ques.getSolution()).append("');");
				st.executeUpdate(insertQuery.toString());
				insertQuery.replace(0, insertQuery.length(), "");
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alotAssignmentToUser(Assignment assignment) {
		List<String> users = new ArrayList<String>();
		users = getAllStudentsByCourse(assignment.getAssignmentTable().getCourse());
		
		Statement st;
		try {
			st = conn.createStatement();
			
			StringBuffer insertQuery = new StringBuffer();
			for (String user: users){
				insertQuery.append("INSERT INTO `" + Constants.USER_DB_TABLES.STUDENT_ASSIGNMENT_TABLE_MAPPER + "` (`" + Constants.STUDENT_ASSIGNMENT_TABLE_COLUMNS.USERNAME
						+ "`, `" + Constants.STUDENT_ASSIGNMENT_TABLE_COLUMNS.ASSIGNMENT_NAME + "`, `" + Constants.STUDENT_ASSIGNMENT_TABLE_COLUMNS.COURSE +  "`, `" + Constants.STUDENT_ASSIGNMENT_TABLE_COLUMNS.IS_ACTIVE
						+ "`, `" + Constants.STUDENT_ASSIGNMENT_TABLE_COLUMNS.SCORE + "`) VALUES ('");
				insertQuery.append(user).append("', '")
				.append(assignment.getAssignmentTable().getAssignmentName()).append("', '")
				.append(assignment.getAssignmentTable().getCourse()).append("', '")
				.append(1).append("', '").append(0).append("');");
				st.executeUpdate(insertQuery.toString());
				insertQuery.replace(0, insertQuery.length(), "");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void alotAssignmentToAdmin(Assignment assignment, String userName) {
		Statement st;
		try {
			st = conn.createStatement();
			
			StringBuffer insertQuery = new StringBuffer();
				insertQuery.append("INSERT INTO `" + Constants.USER_DB_TABLES.STUDENT_ASSIGNMENT_TABLE_MAPPER + "` (`" + Constants.STUDENT_ASSIGNMENT_TABLE_COLUMNS.USERNAME
						+ "`, `" + Constants.STUDENT_ASSIGNMENT_TABLE_COLUMNS.ASSIGNMENT_NAME + "`, `" + Constants.STUDENT_ASSIGNMENT_TABLE_COLUMNS.COURSE +  "`, `" + Constants.STUDENT_ASSIGNMENT_TABLE_COLUMNS.IS_ACTIVE
						+ "`, `" + Constants.STUDENT_ASSIGNMENT_TABLE_COLUMNS.SCORE + "`) VALUES ('");
				insertQuery.append(userName).append("', '")
				.append(assignment.getAssignmentTable().getAssignmentName()).append("', '")
				.append(assignment.getAssignmentTable().getCourse()).append("', '")
				.append(1).append("', '").append(0).append("');");
				st.executeUpdate(insertQuery.toString());
				/*insertQuery.replace(0, insertQuery.length(), "");*/
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
public boolean isUserExistInRoster(String rosterTableName, String userName) {
		
		ResultSet rs = null;
		String UserName = null;
		Statement st;
		try {
			st = conn.createStatement(); 
			
			StringBuffer query = new StringBuffer();
			query.append("SELECT `").append(Constants.ROSTER_COLUMNS.USERNAME).append("` FROM `")
			.append(rosterTableName).append("` WHERE `").append(Constants.ROSTER_COLUMNS.USERNAME)
			.append("`='").append(userName).append("';");
			
		  rs = st.executeQuery(query.toString());
		    while (rs.next()) {
		    	UserName = rs.getString(Constants.ROSTER_COLUMNS.USERNAME);
		    }
		    
		    if (UserName==null){
		    	return false;
		    }else{
		    	return true;
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return true;
		
	}
	
	// Add all course students in roster before professor see's the course roster
	public void addAllStudentsInRoster(String rosterTableName, String course) {
		
		List<String> users = new ArrayList<String>();
		users = getAllStudentsByCourse(course);
		
		Statement st;
		try {
			for (String user: users){
				st = conn.createStatement();
				if(!isUserExistInRoster(rosterTableName, user)){
					HashMap<String, String> userData = new HashMap<String, String>();
					userData = getStudentDataForRoster(user, course);
					
					StringBuffer insertQuery = new StringBuffer();
					insertQuery.append("INSERT INTO `").append(rosterTableName).append("` (`").append(Constants.ROSTER_COLUMNS.USERNAME)
					.append("`, `").append(Constants.ROSTER_COLUMNS.LASTNAME).append("`, `").append(Constants.ROSTER_COLUMNS.FIRSTNAME)
					.append("`, `").append(Constants.ROSTER_COLUMNS.UIN).append("`) VALUES ('").append(userData.get(Constants.ROSTER_COLUMNS.USERNAME)).append("', '")
					.append(userData.get(Constants.ROSTER_COLUMNS.LASTNAME)).append("', '").append(userData.get(Constants.ROSTER_COLUMNS.FIRSTNAME))
					.append("', '").append(userData.get(Constants.ROSTER_COLUMNS.UIN)).append("');");
					
					st.executeUpdate(insertQuery.toString());
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public HashMap<String,String> getStudentDataForRoster(String userName, String course){
		ResultSet rs = null;
		HashMap<String, String> userData = new HashMap<String, String>();
		
		try {
			StringBuffer query = new StringBuffer();
			Statement stmt = conn.createStatement();
			
			query.append("select * from ").append(Constants.USER_DB_TABLES.USER_DATA).append(" where `")
			.append(Constants.USER_DATA_COLUMNS.USERNAME).append("`='").append(userName).append("'")
			.append(" and `").append(Constants.USER_DATA_COLUMNS.COURSE).append("`='").append(course).append("';");
			
			rs = stmt.executeQuery(query.toString());
		
		while (rs.next()) {
			
			userData.put(Constants.ROSTER_COLUMNS.USERNAME, userName);
			userData.put(Constants.ROSTER_COLUMNS.LASTNAME, rs.getString(Constants.USER_DATA_COLUMNS.LASTNAME));
			userData.put(Constants.ROSTER_COLUMNS.FIRSTNAME, rs.getString(Constants.USER_DATA_COLUMNS.FIRSTNAME));
			userData.put(Constants.ROSTER_COLUMNS.UIN, rs.getString(Constants.USER_DATA_COLUMNS.UIN));
			
		}
		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userData;
		
	}
	
	//Remove assignment from student's profile
	public void updateStudentAssignment(String userName, String examSelected, int testScore) {
		
		Statement st;
		try {
			
			st = conn.createStatement();
			StringBuffer updateQuery = new StringBuffer();
			updateQuery.append("UPDATE `").append(Constants.USER_DB_TABLES.STUDENT_ASSIGNMENT_TABLE_MAPPER).append("` SET `")
			.append(Constants.STUDENT_ASSIGNMENT_TABLE_COLUMNS.SCORE).append("` = '").append(testScore).append("', `")
			.append(Constants.STUDENT_ASSIGNMENT_TABLE_COLUMNS.IS_ACTIVE).append("` = '").append(0).append("' WHERE `").append(Constants.STUDENT_ASSIGNMENT_TABLE_COLUMNS.USERNAME)
			.append("` = '").append(userName).append("' AND `").append(Constants.STUDENT_ASSIGNMENT_TABLE_COLUMNS.ASSIGNMENT_NAME)
			.append("` = '").append(examSelected).append("';");
			st.executeUpdate(updateQuery.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Assigns each assignment name to its source Table
		public void tableAssignmentMap(Assignment assignment, String userName) {
			
			Statement st;
			try {
				st = conn.createStatement();
				
				StringBuffer insertQuery = new StringBuffer();
					insertQuery.append("INSERT INTO `" + Constants.USER_DB_TABLES.SOURCE_TABLE_AND_ASSIGNMENT_MAPPER + "` (`" + Constants.SOURCE_TABLE_AND_ASSIGNMENT_COLUMNS.ASSIGNMENT_NAME
							+ "`, `" + Constants.SOURCE_TABLE_AND_ASSIGNMENT_COLUMNS.PROFESSOR_NAME + "`, `" + Constants.SOURCE_TABLE_AND_ASSIGNMENT_COLUMNS.COURSE +  "`, `" +  Constants.SOURCE_TABLE_AND_ASSIGNMENT_COLUMNS.SOURCE_TABLENAME + "`) VALUES ('");
					insertQuery.append(assignment.getAssignmentTable().getAssignmentName()).append("', '")
					.append(userName).append("', '")
					.append(assignment.getAssignmentTable().getCourse()).append("', '")
					.append(assignment.getAssignmentTable().getSourceTableName()).append("');");
					st.executeUpdate(insertQuery.toString());
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	
		// Get Active Assignments for Course logged in for
	public List<String> getActiveUserAssignments(UserDataAccess userData){
		
		ResultSet rs = null;
		List<String> assignments = new ArrayList<String>();
		Statement st;
		try {
			st = conn.createStatement(); 
			
		StringBuffer query = new StringBuffer();
		/*query.append("Select ").append(Constants.STUDENT_ASSIGNMENT_TABLE_COLUMNS.ASSIGNMENT_NAME)
		.append(" from ").append(Constants.USER_DB_TABLES.STUDENT_ASSIGNMENT_TABLE_MAPPER)
		.append(" where ").append(Constants.USER_DATA_COLUMNS.USERNAME).append(" = '").append(userData.getUserLogin().getUserName())
		.append("' and ").append(Constants.STUDENT_ASSIGNMENT_TABLE_COLUMNS.IS_ACTIVE).append(" = ").append(true)
		.append(";");*/
		
		
		query.append("SELECT ").append(Constants.STUDENT_ASSIGNMENT_TABLE_COLUMNS.ASSIGNMENT_NAME)
		.append(" FROM `").append(Constants.USER_DB_TABLES.STUDENT_ASSIGNMENT_TABLE_MAPPER)
		.append("` WHERE `").append(Constants.USER_DATA_COLUMNS.USERNAME).append("` = '").append(userData.getUserLogin().getUserName())
		.append("' AND `").append(Constants.USER_DATA_COLUMNS.COURSE).append("` = '").append(userData.getUserProfile().getCourse())
		.append("' AND `").append(Constants.STUDENT_ASSIGNMENT_TABLE_COLUMNS.IS_ACTIVE).append("` = ").append(true)
		.append(";");
			
			
		  rs = st.executeQuery(query.toString());
		    while (rs.next()) {
		    	String assignment = rs.getString(Constants.STUDENT_ASSIGNMENT_TABLE_COLUMNS.ASSIGNMENT_NAME);
		    	assignments.add(assignment);
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return assignments;
		
	}
	
	public AssignmentTable getAssignmentTable(String examSelected, String course) {
		ResultSet rs = null;
		AssignmentTable assignmentTable = new AssignmentTable();
		Statement st;
		try {
			st = conn.createStatement(); 
			
		StringBuffer query = new StringBuffer();
		query.append("Select * from ").append(Constants.USER_DB_TABLES.SOURCE_TABLE_AND_ASSIGNMENT_MAPPER)
		.append(" where ").append(Constants.SOURCE_TABLE_AND_ASSIGNMENT_COLUMNS.ASSIGNMENT_NAME).append(" = '").append(examSelected)
		.append("' and ").append(Constants.SOURCE_TABLE_AND_ASSIGNMENT_COLUMNS.COURSE).append(" = '").append(course)
		.append("';");
		
		  rs = st.executeQuery(query.toString());
		    while (rs.next()) {
		    	assignmentTable.setAssignmentName(rs.getString(Constants.SOURCE_TABLE_AND_ASSIGNMENT_COLUMNS.ASSIGNMENT_NAME));
		    	assignmentTable.setCourse(rs.getString(Constants.SOURCE_TABLE_AND_ASSIGNMENT_COLUMNS.COURSE));
		    	assignmentTable.setProfessorName(rs.getString(Constants.SOURCE_TABLE_AND_ASSIGNMENT_COLUMNS.PROFESSOR_NAME));
		    	assignmentTable.setSourceTableName(rs.getString(Constants.SOURCE_TABLE_AND_ASSIGNMENT_COLUMNS.SOURCE_TABLENAME));
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return assignmentTable;
	}
	
	
	public List<String> getAllStudentsByCourse(String course){
		
		ResultSet rs = null;
		List<String> users = new ArrayList<String>();
		try {

			Statement stmt = conn.createStatement();

			StringBuffer query = new StringBuffer();
			query.append("SELECT ").append(Constants.USER_DATA_COLUMNS.USERNAME).append(" FROM ")
			.append(Constants.USER_DB_TABLES.USER_DATA).append(" WHERE `").append(Constants.USER_DATA_COLUMNS.USER_ROLE)
			.append("` = ").append(UserRole.STUDENT.getRoleID()).append(" AND `").append(Constants.USER_DATA_COLUMNS.COURSE)
			.append("`='").append(course).append("';");

		    rs = stmt.executeQuery(query.toString());
		    while (rs.next()) {
		    	String user = rs.getString(Constants.USER_DATA_COLUMNS.USERNAME);
		    	users.add(user);
		    }

		return users;	

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		finally
		{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public String getSourceTableName(String assignmentName, String course) {
		
		ResultSet rs = null;
		String sourceTable = "";
		try {

			Statement stmt = conn.createStatement();

			StringBuffer query = new StringBuffer();
			query.append("Select ").append(Constants.SOURCE_TABLE_AND_ASSIGNMENT_COLUMNS.SOURCE_TABLENAME).append(" from `")
			.append(Constants.USER_DB_TABLES.SOURCE_TABLE_AND_ASSIGNMENT_MAPPER).append("` where `").append(Constants.SOURCE_TABLE_AND_ASSIGNMENT_COLUMNS.ASSIGNMENT_NAME)
			.append("` = '").append(assignmentName).append("' AND `").append(Constants.SOURCE_TABLE_AND_ASSIGNMENT_COLUMNS.COURSE).append("` = '").append(course).append("';");

		    rs = stmt.executeQuery(query.toString());
		    while (rs.next()) {
		    	sourceTable = rs.getString(Constants.SOURCE_TABLE_AND_ASSIGNMENT_COLUMNS.SOURCE_TABLENAME);
		    }

		return sourceTable;	

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		finally
		{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public ResultSet getSourceTable(String sourceTable) {
		ResultSet rs = null;
		try {

			Statement stmt = conn.createStatement();

			StringBuffer query = new StringBuffer();
			query.append("Select * From `").append(sourceTable).append("`;");

		    rs = stmt.executeQuery(query.toString());

		return rs;	

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Question> getQuestionSet(String quesTable) {
		List<Question> questions = new ArrayList<Question>();
		ResultSet rs = getSourceTable(quesTable);
		try{
		while (rs.next()) {
	    	SolvedQuestion solQues = new SolvedQuestion();
	    	solQues.setQuestionID(Integer.parseInt(rs.getString(Constants.ASSIGNMENT_TABLE_COLUMNS.QUESTION_ID)));
	    	solQues.setQuesColName(rs.getString(Constants.ASSIGNMENT_TABLE_COLUMNS.QUESTION_COLUMN_NAME));
	    	solQues.setAnalysisType(rs.getString(Constants.ASSIGNMENT_TABLE_COLUMNS.ANALYSIS_TYPE));
	    	solQues.setQuesDesc(rs.getString(Constants.ASSIGNMENT_TABLE_COLUMNS.QUESTION_DESC));
	    	solQues.setPoint(Integer.parseInt(rs.getString(Constants.ASSIGNMENT_TABLE_COLUMNS.POINT)));
	    	solQues.setSolution(Double.parseDouble(rs.getString(Constants.ASSIGNMENT_TABLE_COLUMNS.SOLUTION)));
	    	questions.add(solQues);
	    }
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		return questions;
		
	}

	public boolean validateAssignmentName(String assignmentName, String course) {
		String temp = getSourceTableName(assignmentName, course);
		if(temp.equalsIgnoreCase("")){
			return true;
		}
		return false;
	}
	
	// Check if roster for a course exist in the database
		public boolean isRosterUploaded(String course){
			
			String professorName = getProfessorName(course);
			 if(professorName==""){
				 return false;
			 }else{
				 return true;
			 }
			 /*String tableName = getRosterTableName(course);
			
				ResultSet rs = null;
				
				try { 

					Statement stmt = conn.createStatement();
			 
					StringBuffer qry = new StringBuffer();
					
					qry.append("SELECT * FROM INFORMATION_SCHEMA.COLUMNS where TABLE_NAME=")
					.append("'" + tableName + "'");
					
					rs = stmt.executeQuery(qry.toString());
					
					if(rs.next()) {
						return true;
					}else{
						return false;
					}
			 
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				return false;*/
		}
		
	// Roster table name for the course
		public String getRosterTableName(String course){
			String tableName="";
			String professorName = getProfessorName(course);
			tableName = professorName + "_" + course;
			return tableName;
		}
	
	// get professor name for by course, Roster Table Name = ProfessorName_Course
	public String getProfessorName(String course) {
		
		ResultSet rs = null;
		String UserName = "";
		
		try { 

			Statement stmt = conn.createStatement();

			StringBuffer query = new StringBuffer();
			query.append("SELECT `").append(Constants.PROFESSOR_ROSTER_COLUMNS.PROFESSORNAME).append("` FROM `").append(Constants.USER_DB_TABLES.PROFESSOR_ROSTER_MAP)
			.append("` WHERE `").append(Constants.PROFESSOR_ROSTER_COLUMNS.COURSE).append("` = '").append(course).append("';");

		    rs = stmt.executeQuery(query.toString());
		    
		    while (rs.next()) {
		    	UserName= rs.getString(Constants.PROFESSOR_ROSTER_COLUMNS.PROFESSORNAME);
		    }

		return UserName;	

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	// get professor name for by course, Roster Table Name = ProfessorName_Course
		public String getProfessorFromUserData(String course) {
			
			ResultSet rs = null;
			String UserName = "";
			
			try { 

				Statement stmt = conn.createStatement();

				StringBuffer query = new StringBuffer();
				query.append("SELECT `").append(Constants.USER_DATA_COLUMNS.USERNAME).append("` FROM `").append(Constants.USER_DB_TABLES.USER_DATA)
				.append("` WHERE `").append(Constants.USER_DATA_COLUMNS.COURSE).append("` = '").append(course).append("' AND `")
				.append(Constants.USER_DATA_COLUMNS.USER_ROLE).append("`=").append(UserRole.PROF.getRoleID()).append(";");

			    rs = stmt.executeQuery(query.toString());
			    
			    while (rs.next()) {
			    	UserName= rs.getString(Constants.USER_DATA_COLUMNS.USERNAME);
			    }

			return UserName;	

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
		}
	
	// Add User to Roster
	public void addUserToRoster(String rosterTableName, UserDataAccess userData) {
		
		Statement st;
		try {
			st = conn.createStatement();
			
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO `").append(rosterTableName).append("` (`").append(Constants.ROSTER_COLUMNS.USERNAME)
			.append("`, `").append(Constants.ROSTER_COLUMNS.LASTNAME).append("`, `").append(Constants.ROSTER_COLUMNS.FIRSTNAME)
			.append("`, `").append(Constants.ROSTER_COLUMNS.UIN).append("`) VALUES ('").append(userData.getUserLogin().getUserName()).append("', '")
			.append(userData.getUserProfile().getLastName()).append("', '").append(userData.getUserProfile().getFirstName())
			.append("', '").append(userData.getUserProfile().getUIN()).append("');");
			
			st.executeUpdate(insertQuery.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void UpdateScore(String examSelected, int score,
			RosterTable rosterTable, String userName) {
		
		Statement st;
		
		try {
			st = conn.createStatement();
			
			StringBuffer updateQuery = new StringBuffer();
			updateQuery.append("UPDATE `").append(rosterTable.getProfessorName() + "_" + rosterTable.getCourse()).append("` SET `")
			.append(examSelected).append("` = '").append(score).append("' WHERE `").append(Constants.ROSTER_COLUMNS.USERNAME)
			.append("` = '").append(userName).append("';");
			
			st.executeUpdate(updateQuery.toString());
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// Code to Upload File in DataBase
	@Override
	public void createDBTable(String dbTableName, List<TableColumnMeta> colMetaList) throws DuplicateTableException {
		
		try {
			Statement stmt = conn.createStatement();
			String query  = getCreateTableQuery(dbTableName, colMetaList);
			
			// Create User Uploaded Table in DataBase
			stmt.execute(query);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DuplicateTableException("Error While uploading table. Either duplicate table exist in database for this user or more than one table column had same name");
		}
		
	}

	private String getCreateTableQuery(String dbTableName,
			List<TableColumnMeta> colMetaList) {
		
			StringBuffer query = new StringBuffer();

			if (colMetaList == null) {
				throw new RuntimeException("Empty Column Meta Data");
			}

			query.append("CREATE TABLE `" + dbTableName + "` (");

			for (int i=0; i<colMetaList.size(); i++) {
				
				TableColumnMeta colMeta = colMetaList.get(i);
				String colName = colMeta.tableColName;
				String colDataType = colMeta.colDataType;
				query.append("`" + colName + "` " + colDataType);
				
				if(colMeta.isAutoincrement){
					query.append(" AUTO_INCREMENT ");
				}
				if(colMeta.isNotNull){
					query.append(" NOT NULL ");
				}
				if(colMeta.isDefault){
					query.append(" DEFAULT 0 ");
				}
				if(colMeta.isPrimaryKey){
					query.append(" PRIMARY KEY ");
				}
				
				// All columns except last
				if (i<colMetaList.size()-1) {
					query.append(",");
				}
			}
			query.append(");");
			return query.toString();
		}

	@Override
	public void updateUploadedTable(String query) throws InsertionException {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InsertionException("Invalid Data in file. Please upload a CSV in valid format");
		}
		
	}
	
	public void dropTable(String tableName){
		StringBuffer query = new StringBuffer();
		query.append("DROP TABLE ").append(tableName).append(";");
		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query.toString());
			System.out.println("Table: " + tableName + " deleted due to unsuccessful upload");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateTableMapper(String tableName, HashMap<String, Object> data) {
		
		try {
			Statement st = conn.createStatement();

			StringBuffer colNames = new StringBuffer();
			StringBuffer colValues = new StringBuffer();

			for (Iterator<Entry<String, Object>> iterator = data.entrySet()
					.iterator(); iterator.hasNext();) {

				Entry<String, Object> entry = iterator.next();

				String colName = entry.getKey();
				Object val = entry.getValue();
				
				colNames.append(colName).append(",");

				if (val.getClass().isAssignableFrom(Integer.class)) {
					colValues.append(val).append(",");
				} else if (val.getClass().isAssignableFrom(String.class)) {
					colValues.append("'").append(val).append("',");
				}else{
					colValues.append(val).append(",");
				}
			}
			
		    String a = colNames.substring(0, colNames.lastIndexOf(","));
			String b = colValues.substring(0,colValues.lastIndexOf(","));
			
			StringBuffer query = new StringBuffer();
			
			query.append("INSERT INTO " + tableName);
			query.append(" (" + a + ") ");
			query.append("VALUES");
			query.append(" (" + b + ");");
			String qry = query.toString();
			st.executeUpdate(qry);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	// End of File Upload Code
	
	// Allow only one Professor to register for a course
	public boolean isDuplicateProfessor(String course) {
		
		ResultSet rs = null;
		String UserName = null;
		Statement st;
		try {
			st = conn.createStatement(); 
			
			StringBuffer query = new StringBuffer();
			query.append("SELECT `").append(Constants.USER_DATA_COLUMNS.USERNAME).append("` FROM `")
			.append(Constants.USER_DB_TABLES.USER_DATA).append("` WHERE `").append(Constants.USER_DATA_COLUMNS.COURSE)
			.append("`='").append(course).append("' AND `").append(Constants.USER_DATA_COLUMNS.USER_ROLE)
			.append("`='").append(UserRole.PROF.getRoleID()).append("';");
			
		  rs = st.executeQuery(query.toString());
		    while (rs.next()) {
		    	UserName = rs.getString(Constants.USER_DATA_COLUMNS.USERNAME);
		    }
		    
		    if (UserName==null){
		    	return true;
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}

	


}


