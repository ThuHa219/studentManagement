package edu.hanu.studentManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hanu.studentManagement.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
	Optional<Course> findById(long id);
	}
