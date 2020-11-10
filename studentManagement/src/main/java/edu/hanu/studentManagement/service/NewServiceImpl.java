package edu.hanu.studentManagement.service;

import java.util.List;

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
	public New findById(int id) {
		return newRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("NewServiceImpl.findById(): invalid id"));
	}
	
	public List<New> getNews() {
		return newRepository.findAll();
	}

}
