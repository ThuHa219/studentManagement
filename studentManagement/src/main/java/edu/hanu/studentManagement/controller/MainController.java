package edu.hanu.studentManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.hanu.studentManagement.service.UserService;

@Controller
public class MainController {
	@Autowired
	private UserService userService;
	@RequestMapping(value = { "/login", "/" }, method = RequestMethod.GET)
	public String home(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@RequestMapping("/contact")
	public String contact() {
		return "page-contact";
	}

	@RequestMapping("/file")
	public String file() {
		return "file";
	}
	
	@RequestMapping("/becomeTeacher")
	public String becomeTeacher() {
		userService.becomeTeacher();
		String authorities = userService.getUser().getAuthorities().stream().findFirst().get();
		if(authorities.equals("TEACHER")) { 
		return "redirect:/home";
		} else {
		return "redirect:/student/home";
		}
	}
	
	@RequestMapping("/student/home")
	public String studentHome() {
		return "homepagestudent";
	}
}
