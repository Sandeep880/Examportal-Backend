package com.examportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.entities.User;
import com.examportal.entities.exam.Quiz;
import com.examportal.entities.exam.Result;
import com.examportal.services.ResultService;

@RestController
@CrossOrigin("*")
@RequestMapping("/result")
public class ResultController {
	
	@Autowired
	private ResultService resultService;
	
	@PostMapping("/")
	public ResponseEntity<Result> addResult(@RequestBody Result result)
	{
		Result result1 = this.resultService.addResult(result);
		return ResponseEntity.ok(result1);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Result>> getAllResult()
	{
		List<Result> results = this.resultService.getAllResult();
		return ResponseEntity.ok(results);
	}
	
	@GetMapping("/quiz/{quizId}")
	public ResponseEntity<?> getResultOfQuiz(@PathVariable("quizId") Long quizId)
	{
		Quiz quiz= new Quiz();
		quiz.setQid(quizId);
		return ResponseEntity.ok(this.resultService.getResultOfQuiz(quiz));
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getResultOfUser(@PathVariable("userId") Long userId)
	{
		User user1 = new User();
		user1.setId(userId);
		return ResponseEntity.ok(this.resultService.getResultOfUser(user1));
	}
	
	@GetMapping("/{quizId}/{userId}")
	public ResponseEntity<?> getResultOfUserAndQuiz(@PathVariable("quizId") Long quizId, @PathVariable("userId") Long userId)
	{
		Quiz quiz1 = new Quiz();
		quiz1.setQid(quizId);
		User user1 = new User();
		user1.setId(userId);
		
		System.out.println(quizId + " "+ userId);
		List<Result> list = this.resultService.getResultOfUserAndQuiz(quiz1, user1);
		for(Result li : list)
		{
			System.out.println(li.getMarksScored());
		}
		return ResponseEntity.ok(list);
		
	}
	
	

}
