package org.java.app.service;

import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.java.app.common.Constants.SERVER_RESPONSE;
import org.java.app.model.UserLogin;


@ManagedBean(name="loginService")
@SessionScoped
public class LoginService {

	@ManagedProperty(value="#{login}")
	public UserLogin login;
	
	public HashMap<String, String> users = new HashMap<String, String>();
	
	public LoginService(){
		users.put("mgupta21", "password1");
		users.put("agupta53", "password1");
		users.put("amitk07", "password1");
		users.put("sgill21", "password1");
	}
	
	
	public String authenticate() {
		
		String password = login.getPassword();
		String userId = login.getUserName();
		if (password != null && password.trim() !="" && userId.trim() !="" && userId != null)	{
			if (users.containsKey(userId) && users.get(userId).equals(password)){
				
				return SERVER_RESPONSE.SUCCESS;
				
			}else{
				return SERVER_RESPONSE.FAIL;
			}
		}
		else{
			return SERVER_RESPONSE.FAIL;
		}
}
	
	public String login() {
		// Todo: Track user session and IP data
	
		return null;
	}
	
	public String logout() {
		
		return null;
	}

	public void setlogin(UserLogin login) {
		this.login = login;
	}

	public HashMap<String, String> getUsers() {
		return users;
	}

	public void setUsers(HashMap<String, String> users) {
		this.users = users;
	}
}
