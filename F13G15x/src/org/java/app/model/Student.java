package org.java.app.model;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="student")
@SessionScoped
public class Student extends UserDataAccess{
	
	private List<UserAssignment> userAssignments;

	public List<UserAssignment> getUserAssignments() {
		return userAssignments;
	}

	public void setUserAssignments(List<UserAssignment> userAssignments) {
		this.userAssignments = userAssignments;
	}
	
	
}
