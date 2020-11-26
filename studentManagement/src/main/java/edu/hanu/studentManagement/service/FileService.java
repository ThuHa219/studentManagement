package edu.hanu.studentManagement.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import edu.hanu.studentManagement.model.File;
import edu.hanu.studentManagement.repository.FileRepository;


@Service
public class FileService {
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private UserService userService;
	
	public File storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
		if(fileName.contains("..")) {
			// error
		}
		File dbFile = new File(fileName, file.getContentType(), file.getBytes(), userService.getUser());
		return fileRepository.save(dbFile);
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public File getFile(int fileId) {
		return fileRepository.findById(fileId)
				.orElseThrow(() -> new IllegalArgumentException("error"));
	}
}