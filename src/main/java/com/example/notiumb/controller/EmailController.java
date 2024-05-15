package com.example.notiumb.controller;


import com.example.notiumb.model.User;
import com.example.notiumb.service.IEmailService;
import com.example.notiumb.dto.EmailDTO;
import com.example.notiumb.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    IEmailService emailService;

    @PostMapping("/send")
    private ResponseEntity<String> sendEmail(@RequestBody EmailDTO emailDTO) throws MessagingException {
        emailService.sendEmail(emailDTO);
        return new ResponseEntity<>("Email sent successfully", org.springframework.http.HttpStatus.OK);
    }




}
