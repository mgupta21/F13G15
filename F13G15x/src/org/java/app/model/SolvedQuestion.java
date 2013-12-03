package org.java.app.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="solvedQuestion")
@SessionScoped
public class SolvedQuestion extends Question {

	private Double studentAnswer;
	private int marksObtained;
	
	public SolvedQuestion(){
		System.out.println("Solved Question is Set to Session");
	}
	
	public Double getStudentAnswer() {
		return studentAnswer;
	}
	
	public void setStudentAnswer(Double studentAnswer) {
		this.studentAnswer = studentAnswer;
	}

	public int getMarksObtained() {
		return marksObtained;
	}
	
	public void setMarksObtained(int marksObtained) {
		this.marksObtained = marksObtained;
	}
	
	public void verify() {
		if(Math.abs((super.getSolution()- this.studentAnswer)) < 0.3){
			this.marksObtained = super.getPoint();
		}
	}
}
