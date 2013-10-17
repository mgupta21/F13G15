package org.java.app.service;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.java.app.common.Constants.SERVER_RESPONSE;



@ManagedBean(name="quizSubmitService")


public class QuizSubmitService {
	
	public String submitQuiz()
	{
		return SERVER_RESPONSE.SUCCESS;
	}

}
