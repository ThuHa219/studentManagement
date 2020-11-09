package edu.hanu.studentManagement.service;

import java.util.Optional;

import edu.hanu.studentManagement.model.New;

public interface NewService {
	New save(New news);
	Optional<New> findById(int id);
}
