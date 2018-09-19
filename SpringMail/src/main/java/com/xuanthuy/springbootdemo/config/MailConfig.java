package com.xuanthuy.springbootdemo.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.xuanthuy.springbootdemo.utils.MailInfo;

@Configuration
public class MailConfig {
	
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		mailSenderImpl.setHost("smtp.gmail.com");
		mailSenderImpl.setPort(587);
		
		mailSenderImpl.setUsername(MailInfo.EMAIL_USERNAME);
		mailSenderImpl.setPassword(MailInfo.EMAIL_PASSWORD);
		
		Properties properties = mailSenderImpl.getJavaMailProperties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.debug", "true");
		
		return mailSenderImpl;
	}
}
