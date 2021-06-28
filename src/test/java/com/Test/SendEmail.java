package com.Test;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class SendEmail {

	public static void send_mail() throws EmailException {		
		System.out.println("Started");
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("jennifer.virginiya@gmail.com", "admin$$01"));
		email.setSSLOnConnect(true);
		email.setFrom("jennifer.virginiya@gmail.com");
		email.setSubject("Test Mail from Eclipse");
		email.setMsg("This is a test mail");
		email.addTo("jennifer.virginiya@gmail.com");
		email.send();
		System.out.println("Ended");
	}

}
