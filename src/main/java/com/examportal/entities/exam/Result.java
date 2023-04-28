package com.examportal.entities.exam;

import com.examportal.entities.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int result_id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Quiz quiz;
	
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	private int qAttempted;
	
	private int correctAns;
	
	private double marksScored;
	
	private String submitDateTime;
	
	
	

	public int getResult_id() {
		return result_id;
	}

	public void setResult_id(int result_id) {
		this.result_id = result_id;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getqAttempted() {
		return qAttempted;
	}

	public void setqAttempted(int qAttempted) {
		this.qAttempted = qAttempted;
	}

	public int getCorrectAns() {
		return correctAns;
	}

	public void setCorrectAns(int correctAns) {
		this.correctAns = correctAns;
	}

	public double getMarksScored() {
		return marksScored;
	}

	public void setMarksScored(double marksScored) {
		this.marksScored = marksScored;
	}

	public String getSubmitDateTime() {
		return submitDateTime;
	}

	public void setSubmitDateTime(String submitDateTime) {
		this.submitDateTime = submitDateTime;
	}

	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Result(int result_id, Quiz quiz, User user, int qAttempted, int correctAns, double marksScored,
			String submitDateTime) {
		this.result_id = result_id;
		this.quiz = quiz;
		this.user = user;
		this.qAttempted = qAttempted;
		this.correctAns = correctAns;
		this.marksScored = marksScored;
		this.submitDateTime = submitDateTime;
	}
	
	
	
	
	
	
	
	
	
}
