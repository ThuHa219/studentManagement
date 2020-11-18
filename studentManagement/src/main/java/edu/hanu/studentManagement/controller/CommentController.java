package edu.hanu.studentManagement.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.xml.bind.v2.runtime.unmarshaller.Intercepter;

import edu.hanu.studentManagement.model.Comment;
import edu.hanu.studentManagement.service.CommentService;
import edu.hanu.studentManagement.service.NewService;
import edu.hanu.studentManagement.service.UserService;

@Controller
public class CommentController {
	
	@Autowired
	UserService userService;
	@Autowired
	NewService newService;
	@Autowired
	CommentService commentService;
	
	@RequestMapping(method = RequestMethod.POST, path = {"/createComment/{id}"})
    public String registerUserAccount(@PathVariable("id") int id, @ModelAttribute("news") @Valid Comment comment) {
		comment.setDate(new Date(System.currentTimeMillis()));
		comment.setNews(newService.findById(id));
		comment.setUsers(userService.getUser());
        commentService.save(comment);
        return "redirect:/newSingle/"+id;
    }
	
}
