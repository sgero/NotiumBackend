package com.example.notiumb.service;


import com.example.notiumb.converter.IUserMapper;
import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.model.User;
import com.example.notiumb.repository.IUserRepository;
import com.example.notiumb.service.implementation.EmailServiceImpl;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.math.BigInteger;

import java.util.List;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserMapper iUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private JavaMailSender javaMailSender;

    public List<UserDTO> listarUsers(){

        return iUserMapper.toDTO(userRepository.findAll());

    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findTopByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
    }

    public UserDTO getByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findTopByUsername(username).orElse(null);

        if (user!=null){
            return iUserMapper.toDTO(user);
        }else{
            throw  new UsernameNotFoundException("Usuario no encontrado");
        }

    }

    public UserDTO save(UserDTO userDTO){
        return iUserMapper.toDTO(userRepository.save(iUserMapper.toEntity(userDTO)));
    }

    public Boolean existByCredentials(String username, String password){
        User user = userRepository.findTopByUsername(username).orElse(null);
        return user != null  && passwordEncoder.matches(password,user.getPassword());
    }

    public Boolean existsByUsernameAndPassword(String username, String password){
        return userRepository.existsByUsernameAndPassword(username, password);
    }

    public List<UserDTO> getAllByUsernameAndPassword(String username, String password){
        return iUserMapper.toDTO(userRepository.getAllByUsernameAndPassword(username,password));
    }

    public User obtenerUsuarioPorToken(String token) {
        return (User) userRepository.findTopByToken(token).orElse(null);
    }



    public void actualizarUsuario(User usuario) {
        userRepository.save(usuario);
    }

    public User registrarUsuario(User usuario) throws MessagingException {
        // Lógica para determinar el rol y realizar acciones específicas
        switch(usuario.getRol()) {
            case ADMIN:
                // Para el rol de Administrador, no enviar email de verificación
                usuario.setVerificado(true);
                usuario = userRepository.save(usuario);
                break;
            case CLIENTE:
                // Para el rol de Cliente, enviar email de verificación al email del usuario
                usuario = registrarCliente(usuario);
                break;
            case RESTAURANTE, OCIONOCTURNO:
                // Para los roles de Restaurante y OcioNocturno, enviar email de verificación al email notiumevents@gmail.com
                usuario = registrarEmpresa(usuario);
                break;
            // Otros casos y roles...
            case RPP:
                // Para el rol de RPP, setear el campo verificado como true
                usuario.setVerificado(true);
                usuario = userRepository.save(usuario);
                break;

        }

        return usuario;

}


//REGISTROS
    //REGISTRO DE USUARIO (CLIENTE)
    private User registrarCliente(User usuario) {
        usuario.setVerificado(false); // Establecer el campo verificado como false por defecto
        enviarEmailVerificacion(usuario.getEmail());
        return userRepository.save(usuario);
    }

    private void enviarEmailVerificacion(String email) {
        // Lógica para enviar email de verificación con un enlace único
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(email);
        mensaje.setSubject("Verifica tu cuenta");
        mensaje.setText("Por favor haz clic en el siguiente enlace para verificar tu cuenta: http://localhost:8080/verificar?token=" + generarToken());
        javaMailSender.send(mensaje);
    }

    //REGISTRO DE USUARIO (RESTAURANTE Y OCIO NOCTURNO) "EMPRESAS"
    private User registrarEmpresa(User usuario) throws MessagingException {
        usuario.setVerificado(false); // Establecer el campo verificado como false por defecto
        enviarEmailVerificacionEmpresas();
        return userRepository.save(usuario);
    }

    private void enviarEmailVerificacionEmpresas() throws MessagingException {
        // Lógica para enviar el email de verificación a la dirección de correo especificada
        String enlaceVerificacion = "http://localhost:8080/verificarEmpresa"; // URL sin parámetro idEmpresa
        String mensajeTexto = "Se ha registrado una nueva empresa. Por favor, verifique esta solicitud haciendo clic en el siguiente enlace:\n" + enlaceVerificacion;

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo("notiumevents@gmail.com");
        mensaje.setSubject("Verificación de registro de empresa");
        mensaje.setText(mensajeTexto);
        javaMailSender.send(mensaje); // Utiliza el bean JavaMailSender para enviar el mensaje
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
