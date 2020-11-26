package edu.hanu.studentManagement.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hanu.studentManagement.model.Course;
import edu.hanu.studentManagement.model.CourseDTO;
import edu.hanu.studentManagement.model.File;
import edu.hanu.studentManagement.repository.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private UserService userService;
	
	public Course save(Course course) {
		return courseRepository.save(course);
	}
	
	public void delete(long id) {
		courseRepository.deleteById(id);
	}
	
	public List<Course> getAll() {
		return courseRepository.findAll();
	}
	
	public Course getById(long id) {
		return courseRepository.findById(id).get();
	}
	
	public Course enrollCourse(long id) {
		Course c = getById(id);
		c.getUsers().add(userService.getUser());
		return save(c);
	}
	
	public Course updateCourse(CourseDTO courseDTO, long id) {
		Course c = getById(id);
		c.setDescription(courseDTO.getDescription());
		c.setReference(courseDTO.getReferences());
		return save(c);
	}
}
