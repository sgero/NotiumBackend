package com.example.notiumb.service.implementation;


import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.model.User;
import com.example.notiumb.repository.IUserRepository;
import com.example.notiumb.service.IEmailService;
import com.example.notiumb.dto.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

@Service
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    private IUserRepository usuarioRepository;

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



    //   public void enviarEmailVerificacion(String email, String rol) {
    //     // Lógica para enviar email de verificación con un enlace único
    //     User user = usuarioRepository.findTopByEmail(email);

    //     SimpleMailMessage mensaje = new SimpleMailMessage();
    //     mensaje.setTo(email);
    //    mensaje.setSubject("Verifica tu cuenta");
    //   if (rol.equals("CLIENTE")) {
    //       String tokenverificacion = generarToken();
    //       mensaje.setText("Por favor haz clic en el siguiente enlace para verificar tu cuenta: http://localhost:8080/verificar/cliente?token=" + tokenverificacion);
    //       user.setTokenVerificacion(tokenverificacion);
    //       javaMailSender.send(mensaje);
    //   }
    //  }

    public void enviarEmailVerificacion(String email, String rol) {
        if (!"CLIENTE".equals(rol)) {
            return;  // No enviar email si el rol no es CLIENTE
        }

        User user = usuarioRepository.findTopByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found for email: " + email);
        }

        String tokenVerificacion = generarToken();
        String verificationLink = "http://localhost:8080/verificar/cliente?token=" + tokenVerificacion;

        user.setTokenVerificacion(tokenVerificacion);
        usuarioRepository.save(user);  // Asegúrate de guardar el usuario con el nuevo token

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setTo(email);
        emailDTO.setSubject("Verifica tu cuenta");

        Context context = new Context();
        context.setVariable("verificationMessage", "Por favor haz clic en el siguiente enlace para verificar tu cuenta: " + verificationLink);
        String contentHTML = templateEngine.process("verificado", context);

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(email);
            helper.setSubject("Verifica tu cuenta");
            helper.setText(contentHTML, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error sending email: " + e.getMessage(), e);
        }
    }



    public void enviarEmailContinuidadRegistro(String email, String rol) {
        // Lógica para enviar email para habilitar la continuidad del registro
        User user = usuarioRepository.findTopByEmail(email);
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(email);
        mensaje.setSubject("Valida tu email y continúa tu registro");

        // Mensaje personalizado dependiendo del rol del usuario
        String mensajeTexto = "";
        if (rol.equals("RESTAURANTE")) {
            String tokenverificacion = generarToken();
            mensajeTexto = "Por favor haz clic en el siguiente enlace para continuar con el registro de tu restaurante: http://localhost:8080/verificar/continuarRegistroRestaurante?token=" + tokenverificacion;
            user.setTokenVerificacion(tokenverificacion);
        } else if (rol.equals("OCIONOCTURNO")) {
            String tokenverificacion = generarToken();
            mensajeTexto = "Por favor haz clic en el siguiente enlace para continuar con el registro de tu establecimiento de ocio nocturno: http://localhost:8080/verificar/continuarRegistroOcioNocturno?token=" + tokenverificacion;
            user.setTokenVerificacion(tokenverificacion);
        } else {
            // Manejo de error si el rol no es válido
            mensajeTexto = "Error: Rol no válido";
        }

        mensaje.setText(mensajeTexto);
        javaMailSender.send(mensaje);
    }



    public void enviarEmailVerificacionRestaurante(RestauranteDTO restauranteDTO) throws MessagingException {
        // Lógica para enviar el email de verificación a la dirección de correo especificada
        String enlaceVerificacion = "http://localhost:8080/verificar/verificarRestaurante?idEmpresa=" + restauranteDTO.getCif(); // URL con parámetro idEmpresa
        String mensajeTexto = "Se ha registrado un nuevo restaurante. Por favor, como ADMINISTRADOR verifique esta solicitud haciendo clic en el siguiente enlace:\n" + enlaceVerificacion;

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo("notiumevents@gmail.com");
        mensaje.setSubject("Verificación de registro de restaurante");
        mensaje.setText("Nombre del restaurante: " + restauranteDTO.getNombre() + "\nCIF: " + restauranteDTO.getCif() + "\nTeléfono: " + restauranteDTO.getTelefono() + "\n\n" + mensajeTexto);
        javaMailSender.send(mensaje); // Utiliza el bean JavaMailSender para enviar el mensaje
    }

    public void enviarEmailVerificacionOcioNocturno(OcioNocturnoDTO ocioNocturnoDTO) throws MessagingException {
        // Lógica para enviar el email de verificación a la dirección de correo especificada
        String enlaceVerificacion = "http://localhost:8080/verificar/verificarOcioNocturno?idEmpresa=" + ocioNocturnoDTO.getCif(); // URL con parámetro idEmpresa
        String mensajeTexto = "Se ha registrado un nuevo establecimiento de ocio nocturno. Por favor, como ADMINISTRADOR verifique esta solicitud haciendo clic en el siguiente enlace:\n" + enlaceVerificacion;

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo("notiumevents@gmail.com");
        mensaje.setSubject("Verificación de registro de establecimiento de ocio nocturno");
        mensaje.setText("Nombre del establecimiento: " + ocioNocturnoDTO.getNombre() + "\nCIF: " + ocioNocturnoDTO.getCif() + "\nTeléfono: " + ocioNocturnoDTO.getTelefono() + "\n\n" + mensajeTexto);
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




}
