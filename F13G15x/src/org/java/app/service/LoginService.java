package org.java.app.service;

import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.java.app.common.Constants.SERVER_RESPONSE;
import org.java.app.model.login;


@ManagedBean(name="loginService")

public class LoginService {

	@ManagedProperty(value = "#{lg}")
	private login lg;
	
	HashMap<String, String> users = new HashMap<String, String>();
	
	/*public LoginService(){
		users.put("mgupta21", "password1");
		users.put("agupta53", "password2");
	}*/
	
	public String authenticate(){
		
		String password = lg.getPassword();
		String userId = lg.getUserName();
		if (password != null || password.trim() !="")	{
			if (userId.equals("mgupta21") && password.equals("password1")){
				return SERVER_RESPONSE.SUCCESS;
			}else{
				return SERVER_RESPONSE.FAIL;
			}
		}
		else{
			return SERVER_RESPONSE.FAIL;
		}
}
}
