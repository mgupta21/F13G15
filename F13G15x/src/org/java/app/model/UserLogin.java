package org.java.app.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="userLogin")
@SessionScoped
public class UserLogin {
	
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = covertToHash(password);
	}
	
	public String covertToHash(String password) {

		MessageDigest msg = null;
		StringBuilder s = null;
		
		try {
			
			msg = MessageDigest.getInstance("SHA-256");

			msg.update(password.getBytes());

			byte byteData[] = msg.digest();

			/** byte to hex converter */
			s = new StringBuilder();
			for (int i = 0; i < byteData.length; i++) {
				s.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		
		return s.toString();
	}
	
	
}
