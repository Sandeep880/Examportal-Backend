package com.examportal;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.examportal.entities.Role;
import com.examportal.entities.User;
import com.examportal.entities.UserRole;
import com.examportal.repo.UserRepository;
import com.examportal.services.UserService;

 

@SpringBootApplication
public class ExamportalApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public static void main(String[] args)  {
		SpringApplication.run(ExamportalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting Code");
		
//		System.out.println(this.userRepository.findById(1L).toString());
//		
//		
//		 User user = new User();
//		 
//		 user.setFirstname("Sandeep");
//		 user.setLastname("Kumar");
//		 user.setUsername("Sandeep123");
//		 user.setPassword(this.bCryptPasswordEncoder.encode("abc123"));
//		 user.setEmail("abc@gmail.com"); 
//		 user.setProfile("default.png");
//		 
//		 Role role1 = new Role();
//		 
//		 role1.setRoleId(12L); 
//		 role1.setRoleName("ADMIN");
//		 
//		 Set<UserRole> userRoleSet=new HashSet<>();
//		 UserRole userRole= new UserRole();
//		 userRole.setRole(role1); 
//		 userRole.setUser(user);
//		 
//		 userRoleSet.add(userRole);
//		 
//		 User user1 = this.userService.createUser(user, userRoleSet);
//		 System.out.println(user1.getUsername());
//		 
		
		
		
		
	}

}
