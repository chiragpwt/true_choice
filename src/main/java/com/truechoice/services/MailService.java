package com.truechoice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;

@Service
public class MailService {

	@Autowired	
	private MailSender mailSender;
	
	public void sendTextMail(String from,String to, String subject, String body, String cc, String bcc) throws MessagingException {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setText(body);
		message.setSubject(subject);
		if(cc!=null) {
			String[] multiCc = cc.split(",");
			message.setCc(multiCc);
		}
		if(bcc!=null) message.setBcc(bcc);
		mailSender.send(message);
	}
}
