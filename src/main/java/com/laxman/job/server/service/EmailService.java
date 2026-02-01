package com.laxman.job.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    //@Async
    public void sendOtp(String to, String otp) {
        try {
            log.info("Attempting to send OTP email to {}", to);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("proconnect.auth@gmail.com");
            message.setTo(to);
            message.setSubject("Password Reset OTP");
            message.setText("Your OTP is: " + otp);

            mailSender.send(message);
        } catch (Exception ex) {
            log.error("Failed to send OTP email to {}", to, ex);
        }
    }
}

