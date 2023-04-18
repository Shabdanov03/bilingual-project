package com.example.bilingualb8.services;

import jakarta.mail.MessagingException;

/**
 * Shabdanov Ilim
 **/
public interface EmailService {
    void sendEmail(String to,String subject,String body)throws MessagingException;
}
