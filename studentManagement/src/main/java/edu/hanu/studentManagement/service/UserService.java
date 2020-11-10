package edu.hanu.studentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import edu.hanu.studentManagement.model.User;
import edu.hanu.studentManagement.repository.UserRepository;
import edu.hanu.studentManagement.utils.AuthenticationFacade;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationFacade facade;

	public User getUser() {
		return userRepository.findById(getCurrentUser(facade.getAuthentication()).getUsername())
				.get();
	}
	
	private UserDetails getCurrentUser(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return userDetails;
	}
}
