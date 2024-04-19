package com.example.notiumb.service;

import com.example.notiumb.dto.EmailDTO;
import jakarta.mail.MessagingException;

public interface IEmailService {

    void sendEmail(EmailDTO emailDTO) throws MessagingException;
}
