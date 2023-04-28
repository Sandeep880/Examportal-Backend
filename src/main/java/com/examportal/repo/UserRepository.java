package com.examportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {

	public User findByUsername(String username);
	
	

}
