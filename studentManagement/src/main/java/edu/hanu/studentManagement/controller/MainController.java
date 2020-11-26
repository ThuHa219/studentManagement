package edu.hanu.studentManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
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
}
