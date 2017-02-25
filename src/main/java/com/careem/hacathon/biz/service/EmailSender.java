package com.careem.hacathon.biz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/*
 * @author Ranjan Kumar
 */
@Service
public class EmailSender {

	
	@Autowired
	JavaMailSender javaMailSender;
	public EmailSender() {
		
	}
	Logger logger=LoggerFactory.getLogger(EmailSender.class);
	public void sendMailRequestQuote(String from, String to, String subject, String msg) {

		
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			message.setSubject(subject);
			MimeMessageHelper helper;
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setText(msg, true);
			logger.info("before sending mail");
			javaMailSender.send(message);
			logger.info("Sending mail successfull");
		} catch (MessagingException e) {

			e.printStackTrace();
		}

	}
}
