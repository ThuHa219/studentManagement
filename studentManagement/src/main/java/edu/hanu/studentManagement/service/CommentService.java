package edu.hanu.studentManagement.service;

import java.util.List;

import edu.hanu.studentManagement.model.Comment;

public interface CommentService {
	List<Comment> getComments();

	Comment save(Comment comment);
}
