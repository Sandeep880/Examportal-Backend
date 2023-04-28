package com.examportal.services;

import java.util.Set;

import com.examportal.entities.User;
import com.examportal.entities.UserRole;

public interface UserService {
	
	
	
	// Creating User
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	// creating methods for finding user by username in the user databases
	
	public User getUser(String username);
	
	// creating methods for deleting user from user databases
	public void deleteUser(Long userId);
	
	// updating user details by using userId in databases
	public User updateUser(User user,Long userId) throws Exception;

}
