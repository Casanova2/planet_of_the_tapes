package com.planetofthetapes.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleEmailController {
	private static final String HOST = "smtp.gmail.com";
	private static final int PORT = 465;
	private static final boolean SSL_FLAG = true;
    
    @Autowired
    private JavaMailSender sender;

    @RequestMapping("/simpleemail")
    public String sendEmail(String name,String email, String to, String subject, String body, String from) throws Exception{
        
    	MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
    	
        
        helper.setTo(to);
        helper.setText("Message from "+name+" "+"with email "+email+"\n"+body);
        helper.setSubject(subject);
        helper.setFrom("pottapes@gmail.com");
        
        sender.send(message);
        
        return "redirect:/";
        }
    	
       
    
}