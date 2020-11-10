package edu.hanu.studentManagement.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import edu.hanu.studentManagement.model.Comment;
import edu.hanu.studentManagement.service.CommentService;
import edu.hanu.studentManagement.service.NewService;
import edu.hanu.studentManagement.service.UserService;

@Controller
public class CommentController {
	@Autowired
	CommentService commentService;
	@Autowired
	UserService userService;
	@Autowired
	NewService newService;
	
	@ModelAttribute("comment")
	public Comment getComment() {
		return new Comment();
	}
	
	@PostMapping(path = {"/createComment/{id}"})
    public String createComment(@PathVariable("id") int id, @ModelAttribute("comment") @Valid Comment comment,
        BindingResult result) {
		comment.setUsers(userService.getUser());
		comment.setNews(newService.findById(id));
		comment.setDate(new Date(System.currentTimeMillis()));
        commentService.save(comment);
        return "redirect:/login?success";
    }
}
