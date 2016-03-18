package com.ims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping("/403")
	public String accessDenied() {
		return "errors/403";
	}
}
