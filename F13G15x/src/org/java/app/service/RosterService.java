package org.java.app.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

import org.java.app.model.RosterManager;
import org.java.app.model.RosterTable;

@ManagedBean(name="rosterService")
@SessionScoped
public class RosterService {
	
	@ManagedProperty(value="#{rosterTable}")
	private RosterTable rosterTable;
	
	@ManagedProperty(value="#{rosterManager}")
	private RosterManager rosterManager;
	
	@ManagedProperty(value = "#{dbService}")
	DatabaseServiceImpl dbService;
	
	@ManagedProperty(value="#{userService}")
	UserService userService;
	
	Result result;
	private List<String> columnNames;
	
	public void getRroster(){
		
		System.out.println("In Get Roster");
		setRosterTable();
		
		String rosterTableName = rosterTable.getProfessorName() + "_" + rosterTable.getCourse();
		ResultSet rs = dbService.getSourceTable(rosterTableName);
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			result = ResultSupport.toResult(rs);
			int numberColumns = rsmd.getColumnCount();
			int numberRows = result.getRowCount();
			String columnNameList [] = result.getColumnNames();
			columnNames = new ArrayList<String>();
			for(int i=0; i<numberColumns;i++){
				columnNames.add(columnNameList[i]);
			}
			
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		/*return "ViewRoster";*/
		
	}
	
	public void updateRoster(){
		String examSelected = rosterManager.getExamSelected();
		int Score = rosterManager.getScore();
		setRosterTable();
		dbService.UpdateScore(examSelected, Score, rosterTable, userService.getUserData().getUserLogin().getUserName());
	}
	
	// Professor name should be set from DB to make it work in case of Admin login as well
	public void setRosterTable(){
		String tableName = userService.getUserData().getUserProfile().getCourse();
		String UserName = dbService.getProfessorName(tableName);
		rosterTable.setProfessorName(UserName);
		rosterTable.setCourse(userService.getUserData().getUserProfile().getCourse());
	}


	public DatabaseServiceImpl getDbService() {
		return dbService;
	}


	public void setDbService(DatabaseServiceImpl dbService) {
		this.dbService = dbService;
	}


	public Result getResult() {
		getRroster();
		return result;
	}


	public void setResult(Result result) {
		this.result = result;
	}


	public List<String> getColumnNames() {
		return columnNames;
	}


	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}


	public void setRosterTable(RosterTable rosterTable) {
		this.rosterTable = rosterTable;
	}


	public RosterTable getRosterTable() {
		return rosterTable;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public RosterManager getRosterManager() {
		return rosterManager;
	}

	public void setRosterManager(RosterManager rosterManager) {
		this.rosterManager = rosterManager;
	}

}
