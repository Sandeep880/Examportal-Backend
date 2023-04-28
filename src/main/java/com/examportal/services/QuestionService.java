package com.examportal.services;

import java.util.Set;

import com.examportal.entities.exam.Question;
import com.examportal.entities.exam.Quiz;

public interface QuestionService {
	
	public Question addQuestion(Question question);
	
	public Question updateQuestion(Question question);
	
	public Set<Question> getQuestion();
	
	public Question getQuestion(Long questionId);
	
	public Set<Question> getQuestionOfQuiz(Quiz quiz);
	
	public void deleteQuestion(Long quesId);
	
	public Question get(Long questionId);

}
