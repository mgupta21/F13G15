package org.java.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.java.app.common.Constants;
import org.java.app.model.ColumnDataType;
import org.java.app.model.TableColumn;
import org.java.app.model.UserProfile;
import org.java.app.model.UserRole;
import org.java.app.model.UserTable;

@ManagedBean(name = "dbService")
@ApplicationScoped
public class DatabaseServiceImpl {
	
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
			if(!tableType.equals("NA")){
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
		case "decimal":
			return ColumnDataType.Double;
		case "int":
			return ColumnDataType.Integer;
		case "varchar":
			return ColumnDataType.String;
		}
		return ColumnDataType.Undefined;
	}

	public UserProfile getUserProfile(String userName) {
		UserProfile userProfile = new UserProfile();
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			Statement stmt = conn.createStatement();
			
			query.append("select * from ").append(Constants.USER_DB_TABLES.USER_DATA).append(" where ")
			.append(Constants.USER_DATA_COLUMNS.USERNAME).append(" = '").append(userName).append("'");
			
			rs = stmt.executeQuery(query.toString());
			
			while (rs.next()) {
				userProfile.setFirstName(rs.getString(Constants.USER_DATA_COLUMNS.FIRSTNAME));
				userProfile.setLastName(rs.getString(Constants.USER_DATA_COLUMNS.LASTNAME));
				userProfile.setUIN(rs.getString(Constants.USER_DATA_COLUMNS.UIN));
				int role = rs.getInt(Constants.USER_DATA_COLUMNS.USER_ROLE);
				if(role == UserRole.STUDENT.getRoleID()){
					userProfile.setUserRole(UserRole.STUDENT);
				}else{
					userProfile.setUserRole(UserRole.PROF);
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
	

	
}
