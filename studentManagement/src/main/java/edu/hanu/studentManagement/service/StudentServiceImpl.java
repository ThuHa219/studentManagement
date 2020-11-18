package edu.hanu.studentManagement.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.hanu.studentManagement.model.Student;
import edu.hanu.studentManagement.model.StudentDTO;
import edu.hanu.studentManagement.repository.StudentRepository;
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository repository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public Student save(StudentDTO registration) {
        Student user = new Student();
        user.setName(registration.getName());
        user.setDepartment(registration.getDepartment());
        user.setDateOfBirth(registration.getDob());
        user.setGender(registration.getGender());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setAuthorities(new HashSet<>(Arrays.asList("USER")));
        user.setUsername(registration.getStudentUsername());
        user.setEnabled(true);
        user.setDescription(registration.getDescription());
        return repository.save(user);
    }
	
	@Override
	public Optional<Student> findById(String id) {
		return repository.findById(id);
	}

}
