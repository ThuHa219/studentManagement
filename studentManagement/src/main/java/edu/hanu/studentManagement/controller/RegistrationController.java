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
import org.springframework.web.bind.annotation.RequestMapping;

import edu.hanu.studentManagement.model.Student;
import edu.hanu.studentManagement.model.StudentDTO;
import edu.hanu.studentManagement.service.StudentService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	@Autowired
    private StudentService studentService;
	@GetMapping
	public String showRegistration(Model model) {
		return "register";
	}
	
	@ModelAttribute("user")
	public StudentDTO getStudent() {
		return new StudentDTO();
	}
	
	@PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid StudentDTO studentDTO,
        BindingResult result) {

        Optional<Student> existing = studentService.findById(studentDTO.getStudentUsername());
        if (existing.isPresent()) {
            result.rejectValue("id", null, "There is already an account registered with that id");
        }

        if (result.hasErrors()) {
            return "registration";
        }

        studentService.save(studentDTO);
        return "redirect:/login?success";
    }
}
