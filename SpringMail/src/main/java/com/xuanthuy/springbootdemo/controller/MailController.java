package com.xuanthuy.springbootdemo.controller;

import com.xuanthuy.springbootdemo.utils.*;
import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {

	@Autowired
	private JavaMailSender emailSender;
	
	@RequestMapping(value = "/sendsimplemail")
	@ResponseBody
	public String sendSimpleMail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(MailInfo.TO_EMAIL);
		message.setSubject("Test simple mail");
		message.setText("Hello, I am testing simple mail");
		
		this.emailSender.send(message);
		return "Email send!!!";
	}
	
	
	//Gui email dinh kem tap tin
	@RequestMapping(value = "/sendattachmentemail")
	@ResponseBody
	public String sendAttachmentMail() throws MessagingException {
		MimeMessage message = this.emailSender.createMimeMessage();
		boolean multipart = true;
		MimeMessageHelper helper = new MimeMessageHelper(message, multipart);
		helper.setTo(MailInfo.TO_EMAIL);  //Email can gui den
		helper.setSubject("Test attachmentEmail");
		helper.setText("Hello, I am testing sendAttachmentMail");
        String path1 = "D:/Documents/thuyday.txt";
        String path2 = "D:/Documents/book.sql";
 
        // Attachment 1
        FileSystemResource file1 = new FileSystemResource(new File(path1));
        helper.addAttachment("Txt file", file1);
 
        // Attachment 2
        FileSystemResource file2 = new FileSystemResource(new File(path2));
        helper.addAttachment("Readme", file2);
        
		this.emailSender.send(message);

		return "Email send!!!";
	}
	@ResponseBody
	@RequestMapping(value = "/sendhtmlemail")
	public String sendHTMLMail() throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		boolean multipart = true;
		MimeMessageHelper helper = new MimeMessageHelper(message,multipart,"utf-8");
	       String htmlMsg = "<h3>Im testing send a HTML email</h3>"
	                +"<img src='http://www.apache.org/images/asf_logo_wide.gif'>";
	         
	        message.setContent(htmlMsg, "text/html");
	         
	        helper.setTo(MailInfo.TO_EMAIL); //Email can gui den
	         
	        helper.setSubject("Test send HTML email");
	         
	     
	        this.emailSender.send(message);
	 
	        return "Email Sent!";
	}
}
