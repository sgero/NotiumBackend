package com.example.notiumb.controller;


import com.example.notiumb.service.IEmailService;
import com.example.notiumb.service.model.EmailDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send-email")
public class EmailController {

    @Autowired
    IEmailService emailService;

    @PostMapping("/send-email")
    private ResponseEntity<String> sendEmail(@RequestBody EmailDTO emailDTO) throws MessagingException {
        emailService.sendEmail(emailDTO);
        return new ResponseEntity<>("Email sent successfully", org.springframework.http.HttpStatus.OK);
    }
}
