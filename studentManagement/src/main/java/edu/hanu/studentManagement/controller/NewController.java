package edu.hanu.studentManagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.hanu.studentManagement.model.New;
import edu.hanu.studentManagement.service.NewService;

@Controller("/news")
public class NewController implements org.springframework.web.servlet.mvc.Controller{
	
	@Autowired
	NewService newService;
	
	@GetMapping("/createNew")
	public String showCreateNew(Model model) {
		return "createNew";
	}
	
	@ModelAttribute("news")
	public New getNew() {
		return new New();
	}
	
	@PostMapping
    public String registerUserAccount(@ModelAttribute("news") @Valid New news,
        BindingResult result) {
		System.out.println(news.toString());
        newService.save(news);
        return "redirect:/createNew?success";
    }

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
