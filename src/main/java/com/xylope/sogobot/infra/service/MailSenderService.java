package com.xylope.sogobot.infra.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailSenderService {
    private final JavaMailSender jms;

    @Async
    public void sendEmail(SimpleMailMessage email) {
        jms.send(email);
    }

    public void sendHtmlEmail(String to, String subject, String msg) throws MessagingException {
        MimeMessage message = jms.createMimeMessage();

        message.setSubject(subject);
        MimeMessageHelper helper;

        helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(msg, true);

        jms.send(message);
    }
}
