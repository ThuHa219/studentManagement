package edu.hanu.studentManagement.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.hanu.studentManagement.model.Student;
import edu.hanu.studentManagement.model.User;

@Transactional
public interface StudentRepository extends JpaRepository<Student, String>{
	Optional<Student> findById(String id);
}
