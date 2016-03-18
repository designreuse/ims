package com.ims.controller;

import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

public class AbstractManagement {

	@Autowired
	private HttpServletResponse res;
	
	public SimpleGrantedAuthority getUserRole(){
		Iterator iterator = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator();
		SimpleGrantedAuthority user = null;
		while(iterator.hasNext()){
			user = (SimpleGrantedAuthority)iterator.next();
		}
		return user;
	}
	
	public ModelAndView getRedirectPage(){
		try{
			res.sendRedirect("/ims/login");
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
