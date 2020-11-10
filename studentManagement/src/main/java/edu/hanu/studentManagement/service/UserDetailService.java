package edu.hanu.studentManagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.hanu.studentManagement.model.User;
import edu.hanu.studentManagement.model.UserDetail;
import edu.hanu.studentManagement.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repository.findById(username);
		System.out.println(user.get().toString());
		user.orElseThrow(() -> new UsernameNotFoundException("Can not find id " + username));
		System.out.println(user.get().toString());
		return new UserDetail(user.get());
	}
}
