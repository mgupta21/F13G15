package org.java.app.service;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.java.app.common.Constants.SERVER_RESPONSE;



@ManagedBean(name="quizCreateService")


public class QuizCreateService {

	
	public String createQuiz()
	{
		return SERVER_RESPONSE.SUCCESS;
	}

}