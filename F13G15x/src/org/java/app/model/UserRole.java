package org.java.app.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="userRole")
@SessionScoped
public enum UserRole {
	ADMIN(01), PROF(02), STUDENT(03); 
	
private int roleID;

UserRole (int roleID) {
	this.roleID = roleID;
}

public int getRoleID() {
	return roleID;
}
	
}
