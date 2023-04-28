package com.examportal.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.entities.Role;
import com.examportal.entities.User;
import com.examportal.entities.UserRole;
import com.examportal.repo.UserRepository;
import com.examportal.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder  bCryptPasswordEncoder;

	// Created User

	@PostMapping("/")
	public User createdUser(@RequestBody User user) throws Exception {

		Set<UserRole> roles = new HashSet<>();
        
		// encoding Password with BcryptPasswordEncoder
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		
		Role role = new Role();

		role.setRoleId(45L);
		role.setRoleName("Normal");

		UserRole userRole = new UserRole();

		userRole.setRole(role);
		userRole.setUser(user);

		roles.add(userRole);

		return this.userService.createUser(user, roles);

	}
	// for getting All User from database
	
	@GetMapping("/")
	public List<User> getAllUser(){
		return this.userRepository.findAll();
	}

	// for getting user form database by using username
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userService.getUser(username);

	}

	
	 // for deleting user from database by using id
	 
	 @DeleteMapping("/{id}") 
	 public ResponseEntity<Void> deleteUser(@PathVariable("id") Long userId) 
	 {
	     this.userService.deleteUser(userId);
	     return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	     
	 }
	 

	
	 // for updating user details in database by using id
	  
	 @PutMapping("/{id}") public User updateUser(@RequestBody User user,@PathVariable("id") Long userId)
	 { 
		 User result=null; 
		 try { 
			   result =this.userService.updateUser(user, userId);
			 } catch (Exception e) { 
				  
				 e.printStackTrace(); 
			} 
		 return result; 
	}
	 

}
