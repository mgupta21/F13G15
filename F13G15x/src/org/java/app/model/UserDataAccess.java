package org.java.app.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="uData")
@SessionScoped
public class UserDataAccess {
	
	@ManagedProperty(value = "#{userLogin}")
	private UserLogin userLogin;
	
	@ManagedProperty(value = "#{userProfile}")
	private UserProfile userProfile;
	
	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

}
