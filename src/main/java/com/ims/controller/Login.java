package com.ims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Login {

	@RequestMapping("/login")
	public ModelAndView showUserFrom() {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/loginForFingerPrint",params="fpId")
	public ModelAndView showLogInForm(@RequestParam("fpId") Integer fpId) {
		return new ModelAndView("login");
	}
}
