package edu.hanu.studentManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.hanu.studentManagement.model.New;

public interface NewService {
	New save(New news);
	New findById(int id);
	List<New> getNews();
}
