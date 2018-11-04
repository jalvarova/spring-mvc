package com.bolsadeideas.springboot.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocaleController {

	@GetMapping("/locale")
	public String locate(HttpServletRequest request) {
		
		String finalUrl = request.getHeader("referer"); 
		
		return "redirect:".concat(finalUrl);
	}
}
