package org.java.app.service;

import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.myfaces.custom.date.AbstractHtmlInputDate.UserData;
import org.java.app.common.Constants;
import org.java.app.exceptions.DuplicateUserException;
import org.java.app.model.UserDataAccess;
import org.java.app.model.UserTable;

@ManagedBean(name = "userService")
@SessionScoped
public class UserService {
		
		@ManagedProperty(value = "#{uData}")
		UserDataAccess userData;
		
		@ManagedProperty(value = "#{dbService}")
		DatabaseServiceImpl dbService;
	
		public boolean validateUser(HashMap<String, Object> userDBData) {
			
			if(dbService.isExistingUser(userDBData)){
				return true;
			}else{
				return false;
			}
			
		}
		
		public UserDataAccess registerUser(UserDataAccess userData) throws Exception {
		
		this.userData = userData;
		//this.dbService = new DatabaseServiceImpl(); // application scope
		HashMap<String, Object> userDBData = new HashMap<String, Object>();
		
		userDBData.put(Constants.USER_DATA_COLUMNS.USERNAME, userData.getUserLogin().getUserName());
		
		if(!dbService.isExistingUser(userDBData)){
			
			userDBData.put(Constants.USER_DATA_COLUMNS.PASSWORD, userData.getUserLogin().getPassword());
			userDBData.put(Constants.USER_DATA_COLUMNS.UIN, userData.getUserProfile().getUIN());
			userDBData.put(Constants.USER_DATA_COLUMNS.USER_ROLE, userData.getUserProfile().getUserRole());
			userDBData.put(Constants.USER_DATA_COLUMNS.FIRSTNAME, userData.getUserProfile().getFirstName());
			userDBData.put(Constants.USER_DATA_COLUMNS.LASTNAME, userData.getUserProfile().getLastName());
			userDBData.put(Constants.USER_DATA_COLUMNS.EMAILID, userData.getUserProfile().getEmail());
			
			dbService.createNewUser(userDBData);
			
		}else {
			throw new DuplicateUserException("UserName already Taken. Please choose different user");
		}
		
		return userData;
	}
	
	public void setUserProfile() {
		this.getUserData().setUserProfile(dbService.getUserProfile(userData.getUserLogin().getUserName()));
	}
		
	public List<UserTable> geTablesByUserName() {
		String temp = "NA";
		return dbService.getUserTables(userData.getUserLogin().getUserName(), temp);
	}	
	
	public List<UserTable> geTablesByType(String tableType) {
		return dbService.getUserTables(userData.getUserLogin().getUserName(), tableType);
	}	
		
	public List<Object> getUserTables(String userName) {
		
		return null;
	}
	
	public UserDataAccess getUserData() {
		return userData;
	}

	public void setUserData(UserDataAccess userData) {
		this.userData = userData;
	}
	
	public DatabaseServiceImpl getDbService() {
		return dbService;
	}

	public void setDbService(DatabaseServiceImpl dbService) {
		this.dbService = dbService;
	}

}
