package com.examportal.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.examportal.entities.exam.Question;
import com.examportal.entities.exam.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long>   {

	Set<Question> findByQuiz(Quiz quiz);
	
	
	

}
