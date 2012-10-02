package sportsstore.domain;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailSender {
	private JavaMailSenderImpl mailSender;
	private String from;
	
	protected static Logger logger = Logger.getLogger("MailSender");
	
	public MailSender() {
	}
	
	public MailSender(JavaMailSenderImpl mailSender, String from) {
		this.mailSender = mailSender;
		this.from = from;
	}

	public MimeMessage createMimeMessage() {
		return mailSender.createMimeMessage();
	}
	
	@Async
	public void send(MimeMessage message) {
		try {
			mailSender.send(message);
		}
		
		catch (MailException e) {
			logger.error(e.getMessage());
		}
		
		logger.info("Asynchronous method call of send email — Complete");
	}

	public String getFrom() {
		return from;
	}
}
