package org.java.app.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean(name ="question")
@SessionScoped
public class Question {
	
	private int questionID;
	private String quesColName;
	private String analysisType;
	private String quesDesc;
	private int point;
	private Double solution;
	
	public Question() {
		super();
	}
	public int getQuestionID() {
		return questionID;
	}
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Double getSolution() {
		return solution;
	}
	public void setSolution(Double solution) {
		this.solution = solution;
	}
	public String getQuesColName() {
		return quesColName;
	}
	public void setQuesColName(String quesColName) {
		this.quesColName = quesColName;
	}
	public String getAnalysisType() {
		return analysisType;
	}
	public void setAnalysisType(String analysisType) {
		this.analysisType = analysisType;
	}
	public String getQuesDesc() {
		return quesDesc;
	}
	public void setQuesDesc(String quesDesc) {
		this.quesDesc = quesDesc;
	}
	
}
