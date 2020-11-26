package edu.hanu.studentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hanu.studentManagement.model.Course;
import edu.hanu.studentManagement.repository.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;
	
	public Course save(Course course) {
		return courseRepository.save(course);
	}
	
	public void delete(long id) {
		courseRepository.deleteById(id);
	}
}
