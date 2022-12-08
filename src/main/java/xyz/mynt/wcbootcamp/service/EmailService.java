package xyz.mynt.wcbootcamp.service;

public interface EmailService {

    void sendEmailNotification(String toEmail,String subject, String body);

    void consumeObject(String data);
}
