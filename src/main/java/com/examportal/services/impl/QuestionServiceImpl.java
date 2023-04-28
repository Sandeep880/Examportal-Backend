package com.examportal.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.entities.exam.Question;
import com.examportal.entities.exam.Quiz;
import com.examportal.repo.QuestionRepository;
import com.examportal.services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Override
	public Question addQuestion(Question question) {
		
		return this.questionRepository.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		
		return this.questionRepository.save(question);
	}

	@Override
	public Set<Question> getQuestion() {
		
		return new HashSet<>(this.questionRepository.findAll());
	}

	@Override
	public Question getQuestion(Long questionId) {
		
		return this.questionRepository.findById(questionId).get();
	}

	@Override
	public Set<Question> getQuestionOfQuiz(Quiz quiz) {
		
		return this.questionRepository.findByQuiz(quiz);
	}

	@Override
	public void deleteQuestion(Long quesId) {
		Question question=new Question();
		question.setQuesId(quesId);
		 this.questionRepository.deleteById(quesId);
		
	}

	@Override
	public Question get(Long questionId) {
		return this.questionRepository.getOne(questionId);
	}

	

}
