package edu.hanu.studentManagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hanu.studentManagement.model.New;
import edu.hanu.studentManagement.repository.NewRepository;

@Service
public class NewServiceImpl implements NewService {
	
	@Autowired
	NewRepository newRepository;

	@Override
	public New save(New news) {
		return newRepository.save(news);
	}

	@Override
	public Optional<New> findById(int id) {
		return newRepository.findById(id);
	}

}
