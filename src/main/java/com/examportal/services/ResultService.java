package com.examportal.services;

import java.util.List;

import com.examportal.entities.User;
import com.examportal.entities.exam.Quiz;
import com.examportal.entities.exam.Result;

public interface ResultService {
	
	public Result addResult(Result result);
	
	public List<Result> getAllResult();
	
	public List<Result> getResultOfQuiz(Quiz quiz);
	
	public List<Result> getResultOfUser(User user);
	
	public List<Result> getResultOfUserAndQuiz(Quiz quiz,User user);
	
	
	

}
