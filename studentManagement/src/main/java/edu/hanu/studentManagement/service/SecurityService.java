package edu.hanu.studentManagement.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hanu.studentManagement.model.PasswordResetToken;
import edu.hanu.studentManagement.repository.PasswordResetTokenRepository;

@Service
public class SecurityService {
	@Autowired
	private PasswordResetTokenRepository passwordTokenRepository;

	public String validatePasswordResetToken(String token) {
		final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);

		return !isTokenFound(passToken) ? "invalidToken" : isTokenExpired(passToken) ? "expired" : null;
	}

	private boolean isTokenFound(PasswordResetToken passToken) {
		return passToken != null;
	}

	private boolean isTokenExpired(PasswordResetToken passToken) {
		final Calendar cal = Calendar.getInstance();
		return passToken.getExpiryDate().before(cal.getTime());
	}
}
