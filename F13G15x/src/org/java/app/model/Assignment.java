package org.java.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name="assignment")
@SessionScoped
public class Assignment {
	
	@ManagedProperty(value="#{assignmentTable}")
	private AssignmentTable assignmentTable;
	private String assignmentName;
	int questionCount;
	List<Question> questions;
	@ManagedProperty(value = "#{question}")
	Question question;
	
	public void setQuestions(){
		questions = new ArrayList<Question>();
		for(int i=1;i<=questionCount;i++){
			Question ques = new Question();
			ques.setQuestionID(i);
			ques.setSolution(2.0);
			questions.add(ques);
		}
	}
	
	
	public String getAssignmentName() {
		return assignmentName;
	}
	
	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}
	
	public List<Question> getQuestions() {
		return questions;
	}
	
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	public AssignmentTable getAssignmentTable() {
		return assignmentTable;
	}

	public void setAssignmentTable(AssignmentTable assignmentTable) {
		this.assignmentTable = assignmentTable;
	}

	public void setQuestionsFromDB(List<Question> questions) {
		this.questions = new ArrayList<Question>();
		for(Question ques: questions){
			this.questions.add(ques);
		}
	}
	
}
