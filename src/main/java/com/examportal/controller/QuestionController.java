package com.examportal.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


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

import com.examportal.entities.exam.Question;
import com.examportal.entities.exam.Quiz;
import com.examportal.entities.exam.Result;
import com.examportal.services.QuestionService;
import com.examportal.services.QuizService;
import com.examportal.services.ResultService;
import com.examportal.services.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private UserService userService;
	
	// add question
	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question)
	{
		return ResponseEntity.ok(this.questionService.addQuestion(question));
	}
	
	// Update the question
	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question)
	{
		return ResponseEntity.ok(this.questionService.updateQuestion(question));
	}
	
	// get question quizwise
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qid") Long qid)
	{
		Quiz quiz=this.quizService.getQuiz(qid);
        Set<Question> questions=quiz.getQuestions();
        int totalQuestions=Integer.parseInt(quiz.getNoOfQuestions());
        List<Question> list=new ArrayList(questions);
        if(list.size()>totalQuestions)
        {
            list=list.subList(0,totalQuestions+1);
        }
        // for Hidding the answer in the console
        list.forEach((q)->{
           q.setAnswer("");
        });
        
        
        Collections.shuffle(list);
        System.out.println(list);
        return ResponseEntity.ok(list);
		
		
	}
	// get all quiz
	
	@GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getAllQuestionsOfQuizAdmin(@PathVariable("qid") Long qid)
    {
      Quiz quiz=new Quiz();
      quiz.setQid(qid);
      Set<Question>list=this.questionService.getQuestionOfQuiz(quiz);
      System.out.println(list);
      return ResponseEntity.ok(list);
    }
	
	// get Single Question
	
	@GetMapping("/{quesId}")
	public Question getQuestionById(@PathVariable("quesId") Long quesId)
	{
		return this.questionService.getQuestion(quesId);
	}
	
	// delete question
	
	@DeleteMapping("/{quesId}")
	public void delete(@PathVariable("quesId") Long quesId)
	{
		this.questionService.deleteQuestion(quesId);
	}
	
	// eval quiz
	@PostMapping("/eval-quiz/{userId}")
	public ResponseEntity<?> evalualatingQuiz(@PathVariable("userId") String username,@RequestBody List<Question> questions)
	{
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
	    
	    
	    
		double marksGot = 0;
		int correctAnswer=0;
		int attempted =0;
		Long quizId = null;
		for(Question q : questions)
		{
			quizId=q.getQuiz().getQid();
			Question question = this.questionService.get(q.getQuesId());
			if(question.getAnswer().equals(q.getGivenAnswer()))
			{
				// correct
				correctAnswer++;
				double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/(questions.size());   
				marksGot +=marksSingle;
			}
			if(q.getGivenAnswer() != null )
            {
	             attempted++;
            }
			
			
		}
		Map<String, Object> map = Map.of("marksGot",marksGot,"correctAnswer",correctAnswer,"attempted",attempted);
		System.out.println(quizId + " "+ username);
		
		
		// Adding result to the result table
		
		Result result = new Result();
		result.setQuiz(this.quizService.getQuiz(quizId));
		result.setUser(this.userService.getUser(username));
		result.setqAttempted(attempted);
		result.setCorrectAns(correctAnswer);
		result.setMarksScored(marksGot);
		result.setSubmitDateTime(formatter.format(date));
		
		System.out.println((this.resultService.addResult(result)).toString());
		
		
		return ResponseEntity.ok(map);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
