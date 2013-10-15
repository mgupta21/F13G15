package org.java.app.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.java.app.service.LoginService;
import org.java.app.service.UserService;


@ManagedBean(name="userDataAccess")
public class UserDataAccess {
	
	@ManagedProperty(value = "#{loginService}")
	private LoginService loginService;
	
	@ManagedProperty(value = "#{userProfile}")
	private UserProfile userProfile;
	
	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
