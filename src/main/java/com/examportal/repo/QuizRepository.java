package com.examportal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.entities.exam.Category;
import com.examportal.entities.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
	
	public List<Quiz> findByCategory(Category category);
	
	public List<Quiz> findByActive(Boolean b);
	
	public List<Quiz> findByCategoryAndActive(Category c, Boolean b);
	
	
	
	
	

}
