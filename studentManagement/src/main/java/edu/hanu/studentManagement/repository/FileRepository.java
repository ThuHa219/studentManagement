package edu.hanu.studentManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hanu.studentManagement.model.File;

@Repository
public interface FileRepository extends JpaRepository<File, Integer>{
	Optional<File> findById(int id);
}