package xyz.mynt.wcbootcamp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import xyz.mynt.wcbootcamp.service.EmailService;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${list.of.emails}")
    private List<String> emails;

    @Value("${sender.email}")
    private String fromEmail;

    @Value("${sender.name}")
    private String fromName;

    @Override
    public void sendEmailNotification(String toEmail, String subject, String body) {

        MimeMessagePreparator mailMessage = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(
                    mimeMessage, true,"UTF-8");
            message.setFrom(fromEmail,fromName);
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
        };
        mailSender.send(mailMessage);
        logger.info("Email Sent!");
    }

    @Override
    @KafkaListener(topics = "Product_Notification", groupId = "pn")
    public void consumeObject(String data) {
        emails.forEach((String email) ->{
            sendEmailNotification(email,"New Offers",data);
            logger.info("Email Sent!");
        });
    }
}
