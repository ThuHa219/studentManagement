package edu.hanu.studentManagement.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.hanu.studentManagement.model.Comment;
import edu.hanu.studentManagement.model.New;
import edu.hanu.studentManagement.service.NewService;
import edu.hanu.studentManagement.service.UserService;

@Controller
public class NewController {
	
	@Autowired
	NewService newService;
	@Autowired
	UserService userService;
	
	@GetMapping("/news")
	public String showCreateNew(Model model) {
		return "createNew";
	}
	
	@GetMapping("/newSingle/{id}")
	public String showNew(@PathVariable("id") int id, Model model) {
		model.addAttribute("n", newService.findById(id));
		return "blog-single";
	}
	
	@ModelAttribute("news")
	public New getNew() {
		return new New();
	}
	
	@ModelAttribute("comment")
	public Comment getComment() {
		return new Comment();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = {"/createNew"})
    public String registerUserAccount(@ModelAttribute("news") @Valid New news) {
		news.setUsers(userService.getUser());
		news.setDate(new Date(System.currentTimeMillis()));
		System.out.println(news.toString());
        newService.save(news);
        return "redirect:/news?success";
    }
}
