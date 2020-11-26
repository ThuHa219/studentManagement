package edu.hanu.studentManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {
	
	@GetMapping("/teacher/course")
	public String teacherCourse() {
		return "teacher-course";
	}
}
