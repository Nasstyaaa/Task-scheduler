package org.nastya.emailsender.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.nastya.emailsender.DTO.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {
    @Value("${spring.mail.username}")
    private String sender;

    private final JavaMailSender mailSender;

    public void sendEmail(Message message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(message.getEmail());
        mailMessage.setText(message.getText());
        mailMessage.setSubject(message.getHeader());

        mailSender.send(mailMessage);
    }

}
