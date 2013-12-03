package org.java.app.model;

public class UserAssignment {
	
	String UserName;
	String AssignmentName;
	Boolean IsActive;
	int Score;
	
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getAssignmentName() {
		return AssignmentName;
	}
	public void setAssignmentName(String assignmentName) {
		AssignmentName = assignmentName;
	}
	public Boolean getIsActive() {
		return IsActive;
	}
	public void setIsActive(Boolean isActive) {
		IsActive = isActive;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	
	
}
