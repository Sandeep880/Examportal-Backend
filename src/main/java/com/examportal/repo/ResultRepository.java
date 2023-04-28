package com.examportal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.entities.User;
import com.examportal.entities.exam.Quiz;
import com.examportal.entities.exam.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {
	
	List<Result> findByQuiz(Quiz quiz);
	
	List<Result> findByUser(User user);
	
	List<Result> findByQuizAndUser(Quiz quiz,User user);

}
