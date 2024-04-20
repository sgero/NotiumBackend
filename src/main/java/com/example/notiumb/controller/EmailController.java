package com.example.notiumb.controller;


import com.example.notiumb.model.User;
import com.example.notiumb.service.IEmailService;
import com.example.notiumb.dto.EmailDTO;
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


    @Autowired
    private JavaMailSender javaMailSender;

    public void enviarEmailVerificacion(User usuario) {
        // Lógica para enviar email de verificación con un enlace único
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(usuario.getEmail());
        mensaje.setSubject("Verifica tu cuenta");
        mensaje.setText("Por favor haz clic en el siguiente enlace para verificar tu cuenta: http://tuaplicacion.com/verificar?token=" + generarToken(usuario));
        javaMailSender.send(mensaje);
    }

    private String generarToken(User usuario) {
        // Lógica para generar un token único
        return UUID.randomUUID().toString();
    }
}
