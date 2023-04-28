package com.examportal.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.examportal.services.impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	public UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	public UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtils jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String requestHeaderToken = request.getHeader("Authorization");
		System.out.println(requestHeaderToken);
		String username=null;
		String jwtToken=null;
		
		if(requestHeaderToken != null && requestHeaderToken.startsWith("Bearer "))
		{
			// yes token is available
			jwtToken = requestHeaderToken.substring(7);
			
			try {   
			
			    username = this.jwtUtil.extractUsername(jwtToken);
			    
			}
			catch (ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("Jwt Token has Expired ");
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Error");
			}
		}
		else
		{
			System.out.println("Invalid Token , not start with bearer string");
		}
		
		// Validating Token
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if(this.jwtUtil.validateToken(jwtToken, userDetails))
			{
				// token is valid
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
				            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities() ); 
				
				usernamePasswordAuthenticationToken
				.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		else
		{
			System.out.println("Token is not Valid");
		}
		
		filterChain.doFilter(request, response);
		
	}

}
