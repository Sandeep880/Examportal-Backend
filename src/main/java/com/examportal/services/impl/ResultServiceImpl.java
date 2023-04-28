package com.examportal.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.entities.User;
import com.examportal.entities.exam.Quiz;
import com.examportal.entities.exam.Result;
import com.examportal.repo.ResultRepository;
import com.examportal.services.ResultService;

@Service
public class ResultServiceImpl implements ResultService {
	
	@Autowired
	private ResultRepository resultRepository;

	@Override
	public Result addResult(Result result) {
		return this.resultRepository.save(result);
	}

	@Override
	public List<Result> getAllResult() {
		return this.resultRepository.findAll();
	}

	@Override
	public List<Result> getResultOfQuiz(Quiz quiz) {
		return this.resultRepository.findByQuiz(quiz);
	}

	@Override
	public List<Result> getResultOfUser(User user) {
		return this.resultRepository.findByUser(user);
	}

	@Override
	public List<Result> getResultOfUserAndQuiz(Quiz quiz, User user) {
		return this.resultRepository.findByQuizAndUser(quiz, user);
	}

}
