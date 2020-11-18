package edu.hanu.studentManagement.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hanu.studentManagement.model.Comment;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	Optional<Comment> findById(String id);
}
