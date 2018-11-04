package com.bolsadeideas.springboot.app.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.constants.ConstControllers;
import com.bolsadeideas.springboot.app.util.objects.ObjectsUtils;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(value="error", required=false) String error,
			@RequestParam(value="logout", required=false) String logout,
			Model model, Principal principal, RedirectAttributes flash) {

		if (principal != null) {
			flash.addFlashAttribute("info", "Ya inicio session");
			return ConstControllers.REDIRECT_INIT;
		}
		if (error != null) {
			model.addAttribute("error", "Error Login: Name and User incorrect, please try again");
		}
		if (logout !=null) {
			model.addAttribute("success", "successfully closed session");

		}
		return "login";
	}
}
