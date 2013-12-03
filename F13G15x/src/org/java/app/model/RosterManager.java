package org.java.app.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="rosterManager")
@SessionScoped
public class RosterManager {
	
	public String examSelected;
	public int score;
	
	public String getExamSelected() {
		return examSelected;
	}

	public void setExamSelected(String examSelected) {
		this.examSelected = examSelected;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
