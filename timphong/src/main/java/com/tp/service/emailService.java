package com.tp.service;

public interface EmailService {
    void sendMail(String toEmail, String subject, String body);
}
