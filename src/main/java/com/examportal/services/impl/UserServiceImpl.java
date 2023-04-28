package com.examportal.services.impl;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.entities.User;
import com.examportal.entities.UserRole;
import com.examportal.repo.RoleRepository;
import com.examportal.repo.UserRepository;
import com.examportal.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception  {
		
		// creating user
		
		User localUser = this.userRepository.findByUsername(user.getUsername());
		// checking username is present in database or not
		if(localUser != null)
		{
			System.out.println("Username Already Exits !!");
			throw new Exception("Username Already Exits !!");
		}
		else
		{
			// Create User Here 
			for(UserRole ur: userRoles)
			{
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			localUser = this.userRepository.save(user);
			
			
		}
		return localUser;
	}
    
	// getting user by username
	@Override
	public User getUser(String username) {
		
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void deleteUser(Long userId) {

       this.userRepository.deleteById(userId);
		
	}

	@Override
	public User updateUser(User user, Long userId) throws Exception {

		User localUser = this.userRepository.findByUsername(user.getUsername());
		
		if(localUser.getId() == userId)
		{
			localUser.setFirstname(user.getFirstname());
			localUser.setLastname(user.getLastname());
			localUser.setEmail(user.getEmail());
			localUser.setPassword(user.getPassword());
			localUser.setProfile(user.getProfile());
			localUser.setPhone(user.getPhone());
			localUser.setAbout(user.getAbout());
			
			
			this.userRepository.save(localUser);
		}
		else
		{
			throw new Exception("UserId not exits !!");
		}
		
		
		return localUser;
	}

	

	

}
