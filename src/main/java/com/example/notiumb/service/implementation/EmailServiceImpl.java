package com.example.notiumb.service.implementation;


import com.example.notiumb.service.IEmailService;
import com.example.notiumb.dto.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.math.BigInteger;
import java.security.SecureRandom;

@Service
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;


    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendEmail(EmailDTO emailDTO) throws MessagingException {
        try {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(emailDTO.getTo());
        helper.setSubject(emailDTO.getSubject());
//        helper.setText(emailDTO.getText(), true);
//        helper.setText(templateEngine.process("email-template", emailDTO.getText()), true);

        Context context = new Context();
        context.setVariable("message", emailDTO.getText());
        String contentHTML = templateEngine.process("email", context);

        helper.setText(contentHTML, true);


        javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Error sending email" + e.getMessage(), e);
        }
    }


    public void enviarEmailVerificacion(String email) {
        // Lógica para enviar email de verificación con un enlace único
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(email);
        mensaje.setSubject("Verifica tu cuenta");
        mensaje.setText("Por favor haz clic en el siguiente enlace para verificar tu cuenta: http://localhost:8080/verificar?token=" + generarToken());
        javaMailSender.send(mensaje);
    }

    public void enviarEmailVerificacionEmpresas() throws MessagingException {
        // Lógica para enviar el email de verificación a la dirección de correo especificada
        String enlaceVerificacion = "http://localhost:8080/verificarEmpresa"; // URL sin parámetro idEmpresa
        String mensajeTexto = "Se ha registrado una nueva empresa. Por favor, verifique esta solicitud haciendo clic en el siguiente enlace:\n" + enlaceVerificacion;

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo("notiumevents@gmail.com");
        mensaje.setSubject("Verificación de registro de empresa");
        mensaje.setText(mensajeTexto);
        javaMailSender.send(mensaje); // Utiliza el bean JavaMailSender para enviar el mensaje
    }




    //GENERACIÓN DE TOKEN DE VERIFICACIÓN
    private String generarToken() {
        // Lógica para generar un token único
        // Longitud del token deseada
        int longitudToken = 32;

        // Crear un generador de números aleatorios seguro
        SecureRandom secureRandom = new SecureRandom();

        // Crear un array de bytes para almacenar el token generado
        byte[] bytes = new byte[longitudToken / 2];

        // Generar bytes aleatorios
        secureRandom.nextBytes(bytes);

        // Convertir los bytes a una cadena hexadecimal
        BigInteger bigInteger = new BigInteger(1, bytes);
        String token = bigInteger.toString(16);

        // Asegurar que el token tenga la longitud deseada
        while (token.length() < longitudToken) {
            token = "0" + token;
        }
        return token;
    }




    //    private void enviarEmailVerificacionEmpresas(String correoDestino, Long idEmpresa) throws MessagingException {
//        // Lógica para enviar el email de verificación a la dirección de correo especificada
//        String enlaceVerificacion = "http://localhost:8080/verificarEmpresa?id=" + idEmpresa; // URL con el parámetro idEmpresa
//        String mensajeTexto = "Se ha registrado una nueva empresa. Por favor, verifique esta solicitud haciendo clic en el siguiente enlace:\n" + enlaceVerificacion;
//
//        SimpleMailMessage mensaje = new SimpleMailMessage();
//        mensaje.setTo(correoDestino);
//        mensaje.setSubject("Verificación de registro de empresa");
//        mensaje.setText(mensajeTexto);
//        javaMailSender.send(mensaje); // Utiliza el bean JavaMailSender para enviar el mensaje
//    }

//    private void enviarEmailVerificacionEmpresas() throws MessagingException {
//        // Lógica para enviar el email de verificación a la dirección de correo especificada
//        SimpleMailMessage mensaje = new SimpleMailMessage();
//        mensaje.setTo("notiumevents@gmail.com");
//        mensaje.setSubject("Verificación de registro de empresa");
//        mensaje.setText("Se ha registrado una nueva empresa. Por favor, verifique esta solicitud.");
//        javaMailSender.send(mensaje); // Utiliza el bean JavaMailSender para enviar el mensaje
//    }



    //    @Autowired
//    private JavaMailSender javaMailSender;
//
//    public void enviarEmailVerificacion(User usuario) {
//        // Lógica para enviar email de verificación con un enlace único
//        SimpleMailMessage mensaje = new SimpleMailMessage();
//        mensaje.setTo(usuario.getEmail());
//        mensaje.setSubject("Verifica tu cuenta");
//        mensaje.setText("Por favor haz clic en el siguiente enlace para verificar tu cuenta: http://tuaplicacion.com/verificar?token=" + generarToken(usuario));
//        javaMailSender.send(mensaje);
//    }

//    private String generarToken(User usuario) {
//        // Lógica para generar un token único
//        return UUID.randomUUID().toString();
//    }
}
