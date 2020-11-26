package edu.hanu.studentManagement.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.hanu.studentManagement.model.Student;
import edu.hanu.studentManagement.model.StudentDTO;
import edu.hanu.studentManagement.model.User;
import edu.hanu.studentManagement.model.UserDTO;
import edu.hanu.studentManagement.service.StudentService;
import edu.hanu.studentManagement.service.UserService;

@Controller
public class AccountController {
	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/user/account")
	public String profile(Model model) {
		User user = userService.getUser();
		model.addAttribute("u", user);
		model.addAttribute("numberOfBlog", user.getNews().size());
		model.addAttribute("numberOfComment", user.getComments().size());
		return "profile";
	}
	
	@ModelAttribute("user")
	public UserDTO getUserDTO() {
		return new UserDTO(userService.getUser().getUsername());
	}
	
	@PostMapping("/user/edit")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDTO userDTO,
        BindingResult result) {

        Optional<Student> existing = studentService.findById(userDTO.getUserName());
        Student student = existing.get();
        student.setAcademicAdvior(userDTO.getAcademicAdvior());
        student.setName(userDTO.getName());
        student.setMajor(userDTO.getMajor());
        student.setDepartment(userDTO.getDepartment());
        student.setEmail(userDTO.getEmail());
        student.setDescription(userDTO.getDescription());

        studentService.saveOrUpdate(student);
        return "redirect:/user/account?success";
    }
}
