package org.java.app.service;

import java.util.List;

import org.java.app.exceptions.DuplicateUserException;
import org.java.app.model.UserDataAccess;
import org.java.app.model.UserLogin;
import org.java.app.model.UserProfile;



public interface IUserService {
	
	public UserDataAccess createNewUser(UserProfile userProfile, UserLogin userLogin)
			throws DuplicateUserException;
	
    
    public UserDataAccess authenticateUser(UserLogin userLogin);
    
	
	public List<UserDataAccess> getAllUsers();
	
	public void updateUser(String userName, UserProfile userProfile);
	
	public void deleteUserTables(String userName);
	public void deleteTable(String userName, String tableName);

}
