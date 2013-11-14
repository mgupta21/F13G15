package org.java.app.service;

import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.java.app.common.Constants;
import org.java.app.common.Constants.SESSION_RESPONSE;
import org.java.app.exceptions.DuplicateUserException;
import org.java.app.model.UserDataAccess;
import org.java.app.model.UserLogin;
import org.java.app.model.UserProfile;
import org.java.app.model.UserRole;
import org.java.app.model.UserTable;

@ManagedBean(name="loginService")
@SessionScoped
public class LoginService {
	
	@ManagedProperty(value = "#{uData}")
	private UserDataAccess userData;
	
	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	@ManagedProperty(value="#{userLogin}")
	public UserLogin userLogin;
	
	private int role;
	
	public HashMap<String, String> users = new HashMap<String, String>();
	
	public String authenticate() {
		
		UserLogin userLogin = this.userData.getUserLogin();
		HashMap<String, Object> userDBData = new HashMap<String, Object>();
		
		userDBData.put(Constants.USER_DATA_COLUMNS.USERNAME, userLogin.getUserName());
		userDBData.put(Constants.USER_DATA_COLUMNS.PASSWORD, userLogin.getPassword());
			
		if(userService.validateUser(userDBData)){
			userService.setUserProfile(); // Once the user logs in explicitly set Profile
			return Constants.SERVER_RESPONSE.SUCCESS;
		}
		return Constants.SERVER_RESPONSE.FAIL;
		
	}
		
		
	public String login() {
		// Todo: Track user session and IP data
	
		return null;
	}
	
	public String register() throws Exception{
		//this.userService = new UserService();
		UserProfile userProfile = this.userData.getUserProfile();
		
		if(this.getRole() == UserRole.STUDENT.getRoleID()){
			userProfile.setUserRole(UserRole.STUDENT);
		}else{
			userProfile.setUserRole(UserRole.PROF);
		}
		
		try {
			
			userService.registerUser(userData);
			return Constants.SERVER_RESPONSE.SUCCESS;
			
		} catch (DuplicateUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Constants.SERVER_RESPONSE.FAIL;
		
	}
	
	public String logout() {
		
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();

			final HttpServletRequest req = (HttpServletRequest) ec.getRequest();
			req.getSession(false).invalidate();
			System.out.println(SESSION_RESPONSE.LOGOUT);
			return SESSION_RESPONSE.LOGOUT;
	}

	public HashMap<String, String> getUsers() {
		return users;
	}

	public void setUsers(HashMap<String, String> users) {
		this.users = users;
	}

	public UserDataAccess getUserData() {
		return userData;
	}

	public void setUserData(UserDataAccess userData) {
		this.userData = userData;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}
