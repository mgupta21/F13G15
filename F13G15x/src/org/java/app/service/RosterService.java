package org.java.app.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.java.app.common.Constants;
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
	
	private boolean renderRoster = false;
	
	/*public RosterService(UserService uServ){
		this.userService = uServ;
		this.dbService = uServ.getDbService();
		getRroster();
		if(dbService.isRosterUploaded(userService.getUserData().getUserProfile().getCourse())){
			renderRoster = true;
		}else{
			renderRoster = false;
		}
	}*/
	
	public String viewRoster(){
		
		return Constants.SERVER_RESPONSE.VIEWROSTER;
	}
	
	public boolean getRoster(){
		
		setRosterTable();
		
		renderRoster = dbService.isRosterUploaded(rosterTable.getCourse());
		 if(renderRoster){
			 String rosterTableName = rosterTable.getProfessorName() + "_" + rosterTable.getCourse();
				showAllStudentsInRoster(rosterTableName);
				
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
		 }
		
		return renderRoster;
	}
	
	public void showAllStudentsInRoster(String rosterTableName){
		dbService.addAllStudentsInRoster(rosterTableName, userService.getUserData().getUserProfile().getCourse());
	}
	
	public void updateRoster(){
		String examSelected = rosterManager.getExamSelected();
		int Score = rosterManager.getScore();
		setRosterTable();
		// If roster exist then add user to roster and update user score in roster
		if(dbService.isRosterUploaded(userService.getUserData().getUserProfile().getCourse())){
			if(!dbService.isUserExistInRoster(dbService.getRosterTableName(userService.getUserData().getUserProfile().getCourse()), userService.getUserData().getUserLogin().getUserName())){
				dbService.addUserToRoster(dbService.getRosterTableName(userService.getUserData().getUserProfile().getCourse()), userService.getUserData());
			}
			dbService.UpdateScore(examSelected, Score, rosterTable, userService.getUserData().getUserLogin().getUserName());
		}else{
			System.out.println("Student score was not updated in roster. No roster found for the course");
		}
	}
	
	// Professor name should be set from DB to make it work in case of Admin login as well
	public void setRosterTable(){
		String tableName = userService.getUserData().getUserProfile().getCourse();
		String UserName = dbService.getProfessorName(tableName);
		rosterTable = new RosterTable(); 
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
		//getRoster();
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

	public void setRenderRoster(boolean renderRoster) {
		this.renderRoster = renderRoster;
	}

	public boolean isRenderRoster() {
		return renderRoster;
	}
	
	
	

}
