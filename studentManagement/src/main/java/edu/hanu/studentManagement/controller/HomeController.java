package edu.hanu.studentManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.hanu.studentManagement.model.New;
import edu.hanu.studentManagement.service.NewService;

@Controller
public class HomeController {
	
	@Autowired
	NewService newService;
	
	@RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
	public String home(Model model, String error, String logout) {
		if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
	}
	
	@GetMapping("/home")
	public String user(Model model) {
		model.addAttribute("news", getNews());
		return "homepage";
	}
	
	@ModelAttribute("news")
	public List<New> getNews() {
		return newService.getNews();
	}
}
