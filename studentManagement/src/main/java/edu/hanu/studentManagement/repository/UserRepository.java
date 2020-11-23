package edu.hanu.studentManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hanu.studentManagement.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findById(String id);
	Optional<User> findByEmail(String email);
}
