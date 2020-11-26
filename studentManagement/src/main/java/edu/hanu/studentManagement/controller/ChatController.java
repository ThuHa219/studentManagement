package edu.hanu.studentManagement.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.hanu.studentManagement.service.UserService;

@Controller
public class ChatController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/chat")
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("username", userService.getUser().getUsername());
 
        return "chat";
    }
}
