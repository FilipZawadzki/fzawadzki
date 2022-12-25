package com.test.demo.mail.impl;

import com.test.demo.mail.Mail;
import com.test.demo.mail.MailService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSenderImpl javaMailSender;

    @Override
    public void sendMail(@Valid Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getFrom());
        message.setTo(mail.getTo());
        message.setText(mail.getMessage());
        message.setSubject(mail.getSubject());
        javaMailSender.send(message);
    }
}
