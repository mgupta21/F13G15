package org.java.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.java.app.service.AssignmentService;

@ManagedBean(name="assignmentSolved")
@SessionScoped
public class AssignmentSolved extends Assignment {
	
	//private List<SolvedQuestion> solQuestions;
	private int score = 0;
	
	public int verifySolution(){
		score = 0;
		super.getQuestions();
		for(Question ques: super.getQuestions()){
			SolvedQuestion sq = (SolvedQuestion) ques;
			sq.verify();
		    score += sq.getMarksObtained();
		}
		return score;
	}

	public String reset(){
		
		return "Home";
		
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
