package edu.hanu.studentManagement.service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import edu.hanu.studentManagement.model.Mail;
@Service
public class EmailService {
	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	public void sendSimpleMessage(Mail mail) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		
		helper.addAttachment("Hanu_logo.jpg", new ClassPathResource("/static/images/Hanu_logo.jpg"));
		
		Context context = new Context();
		context.setVariables(mail.getModel());
		String html = templateEngine.process("email-template.html", context);
		System.out.println(mail.toString());
		helper.setTo(mail.getTo());
		helper.setText(html, true);
		helper.setSubject(mail.getSubject());
		helper.setFrom(mail.getFrom());
		
		emailSender.send(message);
		
	}
	
	public Mail createMail(String to, Map<String, Object> model) {
		Mail mail = new Mail();
		mail.setFrom("1701040039@s.hanu.edu.vn");
		mail.setTo(to);
		mail.setSubject("Sending Email with new thing");
		mail.setModel(model);
		return mail;
	}
}
