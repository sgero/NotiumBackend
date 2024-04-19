package com.example.notiumb.service.implementation;


import com.example.notiumb.service.IEmailService;
import com.example.notiumb.dto.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

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
}
