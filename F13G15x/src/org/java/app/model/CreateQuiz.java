package org.java.app.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputHidden;


@ManagedBean(name = "createquiz")
public class CreateQuiz {

	
	
	String questionNumber;
	String questionDesc;
	String answer;
	String method;
	
	private static  List<QuestionItems> quizList;
	
	public List<QuestionItems> getQuizList() {
		return quizList;
	}
	
	public String addQuestion()
	{
		QuestionItems qItems = new QuestionItems(this.questionDesc, this.questionNumber,this.answer,this.method);
		quizList.add(qItems);
		return null;
	}
	public void setQuizList(List<QuestionItems> quizList) {
		CreateQuiz.quizList = quizList;
	}
	
	public String deleteQuestion(QuestionItems qItems)
	{
		quizList.remove(qItems);
		return null;
	}
	public CreateQuiz()
	{
		//count.setValue(0);
		quizList = new ArrayList<QuestionItems>();
	}
	
	
	



	
	
	
	public class QuestionItems {

		private String question;
		private String questionNumber;
		private String rightAnswer;
		private String method;
		
				
		public QuestionItems(String question, String questionNumber, String rightAnswer,String method)
		{
			
			this.question = question;
			this.questionNumber = questionNumber;
			this.rightAnswer = rightAnswer;
			this.method = method;
		}

		

	

		public String getRightAnswer() {
			return rightAnswer;
		}

		public void setRightAnswer(String rightAnswer) {
			this.rightAnswer = rightAnswer;
		}

		public String getQuestionNumber() {
			return questionNumber;
		}

		public void setQuestionNumber(String questionNumber) {
			this.questionNumber = questionNumber;
		}

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public String getMethod() {
			return method;
		}

		public void setMethod(String method) {
			this.method = method;
		}
	}

}


