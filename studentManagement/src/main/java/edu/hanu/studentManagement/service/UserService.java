package edu.hanu.studentManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.hanu.studentManagement.model.PasswordResetToken;
import edu.hanu.studentManagement.model.User;
import edu.hanu.studentManagement.repository.PasswordResetTokenRepository;
import edu.hanu.studentManagement.repository.UserRepository;
import edu.hanu.studentManagement.utils.AuthenticationFacade;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationFacade facade;
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User getUser() {
		return userRepository.findById(getCurrentUser(facade.getAuthentication()).getUsername())
				.get();
	}
	
	private UserDetails getCurrentUser(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return userDetails;
	}
	
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email).get();
	}
	
	public void createPasswordResetTokenForUser(User user, String token) {
	    PasswordResetToken myToken = new PasswordResetToken(token, user);
	    passwordResetTokenRepository.save(myToken);
	}
	
	public Optional<User> getUserByPasswordResetToken(final String token) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(token) .getUser());
    }
	
	public void changeUserPassword(User user, String password) {
	    user.setPassword(passwordEncoder.encode(password));
	    userRepository.save(user);
	}
	
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	public User becomeTeacher() {
		User user = getUser();
		user.getAuthorities().add("TEACHER");
		return userRepository.save(user);
	}

}
