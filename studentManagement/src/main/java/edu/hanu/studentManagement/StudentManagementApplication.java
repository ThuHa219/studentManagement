package edu.hanu.studentManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import edu.hanu.studentManagement.repository.CommentRepository;
import edu.hanu.studentManagement.repository.NewRepository;
import edu.hanu.studentManagement.repository.StudentRepository;
import edu.hanu.studentManagement.repository.UserRepository;

@SpringBootApplication

@EnableJpaRepositories(basePackageClasses = {UserRepository.class, StudentRepository.class,NewRepository.class,CommentRepository.class})
public class StudentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

}
