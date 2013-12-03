package org.java.app.service;

import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.java.app.common.Constants;
import org.java.app.common.Constants.SESSION_RESPONSE;
import org.java.app.exceptions.DuplicateUserException;
import org.java.app.exceptions.RestrictionException;
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
	
	public LoginService(){
		System.out.println("In Login Service");
	}
	
	public HashMap<String, String> users = new HashMap<String, String>();
	
	public String tableSet(){
		return "true";
	}
	
	public String authenticate() {
		
		UserLogin userLogin = this.userData.getUserLogin();
		HashMap<String, Object> userDBData = new HashMap<String, Object>();
		
		userDBData.put(Constants.USER_DATA_COLUMNS.USERNAME, userLogin.getUserName());
		userDBData.put(Constants.USER_DATA_COLUMNS.PASSWORD, userLogin.getPassword());
		
		// Admin is independent of course
		if(!userService.isAdmin(userLogin.getUserName())){
			// Remove line below to restrict access for only one course
			userDBData.put(Constants.USER_DATA_COLUMNS.COURSE, userData.getUserProfile().getCourse());
		}
		
		if(userService.validateUser(userDBData)){
			userService.setUserProfile(); // Once the user logs in explicitly set Profile
			return Constants.SERVER_RESPONSE.SUCCESS;
		}
		return Constants.SERVER_RESPONSE.FAIL;
		
	}
		
	public boolean isAdmin() {
		if(this.userData.getUserProfile().getUserRole().getRoleID() == 1){
			return true;
		}
		return false;
	}
	
	public boolean isProf() {
		if(this.userData.getUserProfile().getUserRole().getRoleID() == 2){
			return true;
		}
		return false;
	}
	
	public boolean isStudent() {
		if(this.userData.getUserProfile().getUserRole().getRoleID() == 3){
			return true;
		}
		return false;
	}
	
	public String register() throws Exception{
		//this.userService = new UserService();
		UserProfile userProfile = this.userData.getUserProfile();
		
		if(this.getRole() == UserRole.STUDENT.getRoleID()){
			userProfile.setUserRole(UserRole.STUDENT);
		}else{
			userProfile.setUserRole(UserRole.PROF);
			boolean singleProfessor = userService.isOnlyProfessor(userData.getUserProfile().getCourse());
			if(!singleProfessor){
				getFacesMessage(FacesMessage.SEVERITY_ERROR, "A Professor is already assigned for this course. Please register for different course");
				return Constants.SERVER_RESPONSE.ERROR;
			}
			
		}
		
		try {
			if(userService.getUserData().getUserLogin().getUserName().equalsIgnoreCase("ADMIN")){
				throw new RestrictionException("UserName Restricted");
			}} catch (RestrictionException e) {
				getFacesMessage(FacesMessage.SEVERITY_ERROR, "UserName already taken. Please choose different name");
				e.printStackTrace();
				return Constants.SERVER_RESPONSE.ERROR;
			}
		
		try {
			
			userService.registerUser(userData);
			return Constants.SERVER_RESPONSE.SUCCESS;
			
		} catch (DuplicateUserException e) {
			getFacesMessage(FacesMessage.SEVERITY_ERROR, "User already registered for this course. Please choose different user or course");
			e.printStackTrace();
			return Constants.SERVER_RESPONSE.ERROR;
			
		}
		
	}
	
	public String logout() {
		
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();

			final HttpServletRequest req = (HttpServletRequest) ec.getRequest();
			req.getSession(false).invalidate();
			return SESSION_RESPONSE.LOGOUT;
	}
	
	public void getFacesMessage(Severity severity, String message){
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, new FacesMessage(severity, message, null));
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
