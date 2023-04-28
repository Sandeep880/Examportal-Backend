package com.examportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.examportal.services.impl.UserDetailsServiceImpl;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class MySecurityConfig  {
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	public BCryptPasswordEncoder  passwordEncoder() {
		return new BCryptPasswordEncoder() ;
	}
	
	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      
		http
		     .csrf()
		     .disable()
              .cors()
              .disable()
              .authorizeRequests()
              .requestMatchers("/generate-token","/user/").permitAll()
		      .requestMatchers(HttpMethod.OPTIONS).permitAll()
		      .anyRequest().authenticated()
		      .and()
		      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
		      .and()
		      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		      
		 http.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);   
		 
		 http.authenticationProvider(daoAuthenticationProvider());
		 
		 DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();
		 
		 return defaultSecurityFilterChain;
              
              
    }
	
//	@Bean
//	public void  configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(passwordEncoder());
//	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.userDetailsServiceImpl);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	
}