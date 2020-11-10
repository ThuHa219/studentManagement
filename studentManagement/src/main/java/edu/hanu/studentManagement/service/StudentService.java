package edu.hanu.studentManagement.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.hanu.studentManagement.model.Student;
import edu.hanu.studentManagement.model.StudentDTO;

public interface StudentService {
	Student save(StudentDTO registration);
	Optional<Student> findById(String id);
}
