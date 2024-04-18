package com.example.notiumb.service;

import com.example.notiumb.service.model.EmailDTO;
import jakarta.mail.MessagingException;

public interface IEmailService {

    public void sendEmail(EmailDTO emailDTO) throws MessagingException;
}
