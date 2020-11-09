package edu.hanu.studentManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hanu.studentManagement.model.New;

@Repository
public interface NewRepository extends JpaRepository<New, Integer>{
	Optional<New> findById(String id);
}
