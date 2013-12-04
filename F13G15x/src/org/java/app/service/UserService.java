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
import org.java.app.model.UserRole;
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
		HashMap<String, Object> userDBData = new HashMap<String, Object>();
		
		userDBData.put(Constants.USER_DATA_COLUMNS.USERNAME, userData.getUserLogin().getUserName());
		userDBData.put(Constants.USER_DATA_COLUMNS.COURSE, userData.getUserProfile().getCourse());
		
		if(!dbService.isExistingUser(userDBData)){
			
			userDBData.put(Constants.USER_DATA_COLUMNS.PASSWORD, userData.getUserLogin().getPassword());
			userDBData.put(Constants.USER_DATA_COLUMNS.UIN, userData.getUserProfile().getUIN());
			userDBData.put(Constants.USER_DATA_COLUMNS.USER_ROLE, userData.getUserProfile().getUserRole());
			userDBData.put(Constants.USER_DATA_COLUMNS.FIRSTNAME, userData.getUserProfile().getFirstName());
			userDBData.put(Constants.USER_DATA_COLUMNS.LASTNAME, userData.getUserProfile().getLastName());
			userDBData.put(Constants.USER_DATA_COLUMNS.EMAILID, userData.getUserProfile().getEmail());
			userDBData.put(Constants.USER_DATA_COLUMNS.COURSE, userData.getUserProfile().getCourse());
			
			dbService.createNewUser(userDBData);
			
			if(userData.getUserProfile().getUserRole().getRoleID()==UserRole.STUDENT.getRoleID()){
				if(dbService.isRosterUploaded(userData.getUserProfile().getCourse())){
					dbService.addUserToRoster(dbService.getRosterTableName(userData.getUserProfile().getCourse()), userData);				
				}
				
			}
			
		}else {
			throw new DuplicateUserException("User already registered for this course. Please choose different user or course");
		}
		
		return userData;
	}
	
	public void setUserProfile() {
		this.getUserData().setUserProfile(dbService.getUserProfile(userData));
	}
		
	public List<UserTable> getTablesByUserName() {
		return dbService.getUserTables(userData.getUserLogin().getUserName(), null);
	}	
	
	public List<UserTable> getUserAssignmentTables() {
		return dbService.getUserTables(userData.getUserLogin().getUserName(), Constants.TABLE_TYPES.ASSIGNMENT_TABLE);
	}
	
	public List<UserTable> getUserIndividualTables() {
		return dbService.getUserTables(userData.getUserLogin().getUserName(), Constants.TABLE_TYPES.INDIVIDUAL);
	}	
	
	public List<UserTable> getUserRosterTables() {
		return dbService.getUserTables(userData.getUserLogin().getUserName(), Constants.TABLE_TYPES.ROOSTER);
	}
		
	public List<String> getUserAssignments(){
		return dbService.getActiveUserAssignments(userData);
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

	public boolean isOnlyProfessor(String course) {
		return dbService.isDuplicateProfessor(course);
	}

	public boolean isAdmin(String userName) {
		return dbService.isRoleAdmin(userName);
	}

}
