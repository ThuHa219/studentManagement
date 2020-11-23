package edu.hanu.studentManagement.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.hanu.studentManagement.model.GenericResponse;
import edu.hanu.studentManagement.model.PasswordDTO;
import edu.hanu.studentManagement.model.User;
import edu.hanu.studentManagement.service.SecurityService;
import edu.hanu.studentManagement.service.UserService;

@RestController
public class RegistrationRestController {
	@Autowired
	private UserService userService;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private MessageSource messages;
	@Autowired
	private Environment env;
	@Autowired
	private SecurityService securityService;

	@PostMapping("/resetPassword")
	public GenericResponse resetPassword(final HttpServletRequest request,
			@RequestParam("email") final String userEmail) {
		final User user = userService.getUserByEmail(userEmail);
		System.out.println(user.toString());
		if (user != null) {
			final String token = UUID.randomUUID().toString();
			userService.createPasswordResetTokenForUser(user, token);
			mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));
		}
		return new GenericResponse(messages.getMessage("message.resetPasswordEmail", null, request.getLocale()));
	}
	
	@PostMapping("/user/savePassword")
	public GenericResponse savePassword(final Locale locale, @Valid PasswordDTO passwordDto) {
	 
	    String result = securityService.validatePasswordResetToken(passwordDto.getToken());
	 
	    if(result != null) {
	        return new GenericResponse(messages.getMessage(
	            "auth.message." + result, null, locale));
	    }
	 
	    Optional<User> user = userService.getUserByPasswordResetToken(passwordDto.getToken());
	    if(user.isPresent()) {
	        userService.changeUserPassword(user.get(), passwordDto.getNewPassword());
	        return new GenericResponse(messages.getMessage(
	            "message.resetPasswordSuc", null, locale));
	    } else {
	        return new GenericResponse(messages.getMessage(
	            "auth.message.invalid", null, locale));
	    }
	}

	private SimpleMailMessage constructEmail(String subject, String body, User user) {
		final SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(subject);
		email.setText(body);
		email.setTo(user.getEmail());
		email.setFrom(env.getProperty("support.email"));
		return email;
	}

	private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale,
			final String token, final User user) {
		final String url = contextPath + "/user/changePassword?token=" + token;
		final String message = messages.getMessage("message.resetPassword", null, locale);
		return constructEmail("Reset Password", message + " \r\n" + url + "\r\n" + "Token: " + token, user);
	}

	private String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}
}
