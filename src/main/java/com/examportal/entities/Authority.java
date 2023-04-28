package com.examportal.entities;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

	private String authority;
	
	
	
	public Authority() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Authority(String authority) {
		
		this.authority = authority;
	}





	@Override
	public String getAuthority() {
		
		return this.authority;
	}
	
	

}
