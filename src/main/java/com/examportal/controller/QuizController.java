package com.examportal.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.entities.exam.Category;
import com.examportal.entities.exam.Quiz;
import com.examportal.services.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	
	// add quiz service
	@PostMapping("/")
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz)
	{
		return ResponseEntity.ok(this.quizService.addQuiz(quiz));
	}
	
	// update quiz
	@PutMapping("/")
	public ResponseEntity<Quiz> update(@RequestBody Quiz quiz)
	{
		return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
	}
	
	
	// getting quiz
	@GetMapping("/")
	public ResponseEntity<?> quizzes()
	{
		return ResponseEntity.ok(this.quizService.getQuizzes());
	}
	
	// get Single Quiz
	@GetMapping("/{qid}")
	public Quiz quiz(@PathVariable("qid") Long qid)
	{
		return this.quizService.getQuiz(qid);
	}
	
	// delete
	@DeleteMapping("/{qid}")
	public void delete(@PathVariable("qid") Long qid)
	{
		
		this.quizService.deleteQuiz(qid);
	}
	

    //get quiz of particular category
    @GetMapping("/category/{cid}")
    public List<Quiz> getQuizOfCategory(@PathVariable("cid") Long cid)
    {
    	Category cat=new Category();
    	cat.setCid(cid);
    	List<Quiz> list =(this.quizService.getQuizzesOfCategory(cat));
    	Collections.shuffle(list);
    	return list;
    	
    }
    
    // get active quizzes
    @GetMapping("/active")
    public List<Quiz> getActiveQuizzes()
    {
    	return this.quizService.getActiveQuizzes();
    }
    
    // get active quizzes of category
    @GetMapping("/category/active/{cid}")
    public List<Quiz> getActiveQuizzesOfCategory(@PathVariable("cid") Long cid){
    	Category c =new Category();
    	c.setCid(cid);
    	return this.quizService.getActiveQuizzesOfCategory(c);
    }
	
	
	

}
