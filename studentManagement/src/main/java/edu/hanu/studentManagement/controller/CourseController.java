package edu.hanu.studentManagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import edu.hanu.studentManagement.model.Course;
import edu.hanu.studentManagement.model.CourseDTO;
import edu.hanu.studentManagement.service.CourseService;
import edu.hanu.studentManagement.service.FileService;

@Controller
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	@Autowired
	private FileService fileService;
	
	@GetMapping("/teacherCourse/{id}")
	public String teacherCourse(@PathVariable("id") long id, Model model) {
		model.addAttribute("c", courseService.getById(id));
		model.addAttribute("files", fileService.getAll());
		return "teacher-course";
	}
	
	@GetMapping("/manage/course")
	public String manageCourse(Model model) {
		List<Course> courses = courseService.getAll();
		model.addAttribute("courses", courses);
		return "manage-course";
	}
	
	@GetMapping("/listCourse")
	public String listCourse(Model model) {
		model.addAttribute("courses", courseService.getAll());
		return "list-course";
	}
	
	@PostMapping("/addCourse")
    public String createCourse(@ModelAttribute("course") @Valid CourseDTO courseDTO,
        BindingResult result) {
		Course course = new Course();
		course.setName(courseDTO.getName());

        courseService.save(course);
        return "redirect:/manage/course?success";
    }
	
	@GetMapping("/deleteCourse/{id}")
	public String deleteCourse(@PathVariable("id") long id) {
		courseService.delete(id);
		return "redirect:/manage/course?success";
	}
	
	@GetMapping("/enroll/{id}")
	public String enrollCourse(@PathVariable("id") long id) {
		courseService.enrollCourse(id);
		return "redirect:/listCourse?success";
	}
	
	@ModelAttribute("course")
	public CourseDTO getCourse() {
		return new CourseDTO();
	}
	
	@PostMapping("/updateCourse/{id}")
    public String updateCourse(@ModelAttribute("course") @Valid CourseDTO courseDTO, @PathVariable("id") long id,
        BindingResult result) {
        courseService.updateCourse(courseDTO, id);
        return "redirect:/teacherCourse/"+id+"?success";
    }
	
	
	@GetMapping("/studentCourse/{id}")
	public String studentCourse(@PathVariable("id") long id, Model model) {
		model.addAttribute("c", courseService.getById(id));
		model.addAttribute("files", fileService.getAll());
		return "student-course";
	}
}
