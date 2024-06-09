package com.example.notiumb.service;


import com.example.notiumb.converter.IUserMapper;
import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.model.User;
import com.example.notiumb.model.enums.Rol;
import com.example.notiumb.repository.*;
import com.example.notiumb.security.service.JWTService;
import com.example.notiumb.service.implementation.EmailServiceImpl;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.imageio.event.IIOReadProgressListener;
import java.util.List;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IOcioNocturnoRepository ocioNocturnoRepository;

    @Autowired
    private IUserMapper iUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private JWTService jwtService;

    public List<UserDTO> listarUsers() {

        return iUserMapper.toDTO(userRepository.findAll());

    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findTopByUsername(username);
    }

    public UserDTO getByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findTopByUsername(username);

        if (user != null) {
            return iUserMapper.toDTO(user);
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

    }

    public UserDTO save(UserDTO userDTO) {
        return iUserMapper.toDTO(userRepository.save(iUserMapper.toEntity(userDTO)));
    }

    public Boolean existByCredentials(String username, String password) {
        User user = userRepository.findTopByUsername(username);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    public String deleteUser(User user) {
        user.setActivo(false);
        return "Usuario eliminado";
    }

    public User obtenerUsuarioPorToken(String tokenVerificacion) {
        return userRepository.findTopByTokenVerificacion(tokenVerificacion);
    }



    public void actualizarUsuario(User usuario) {
        userRepository.save(usuario);
    }

    public Object traerPerfilVinculadoUsuario(User user) {
        if (user.getRol() == Rol.CLIENTE){

            return clienteRepository.findByIdUser(user.getId());

        } else if (user.getRol() == Rol.RESTAURANTE) {

            return null; //restauranteRepository.findByIdUser(user.getId());

        }else if (user.getRol() == Rol.OCIONOCTURNO) {

            return ocioNocturnoRepository.findByIdUser(user.getId());

        }else if (user.getRol() == Rol.RPP) {

            return null; //rppRepository.findByIdUserAndActivoTrue(user.getId());

        }

        return user;

    }


    public void registrarUsuario(User usuario) throws MessagingException {
        // Determinar el rol del usuario
        Rol rol = usuario.getRol();

        // Lógica específica basada en el rol del usuario
        switch (rol) {
            case ADMIN:
                // Para el rol de Administrador, no enviar email de verificación
                usuario.setVerificado(true);
                break;
            case CLIENTE:
                // Para el rol de Cliente, enviar email de verificación al email del usuario
                usuario.setVerificado(false); // Establecer el campo verificado como false por defecto
                emailService.enviarEmailVerificacion(usuario.getEmail(), "CLIENTE");
                break;
            case RESTAURANTE:

                usuario.setVerificado(false); // Establecer el campo verificado como false por defecto
                emailService.enviarEmailContinuidadRegistro(usuario.getEmail(), "RESTAURANTE");
                break;
            case OCIONOCTURNO:
                //VAMOS A HACER QUE EL USUARIO SE VERIFIQUE, PERO QUE ADEMÁS LA LÓGICA DE ACTIVACIÓN DE LA CUENTA SEA DIFERENTE Y NECESITE AL ADMIN
                usuario.setVerificado(false); // Establecer el campo verificado como false por defecto
                emailService.enviarEmailContinuidadRegistro(usuario.getEmail(), "OCIONOCTURNO");
                break;
            // Otros casos y roles...
            case RPP:
                // Para el rol de RPP, setear el campo verificado como true
                usuario.setVerificado(true);
                break;
        }

        // Guardar el usuario en el repositorio
        userRepository.save(usuario);
    }



    public User getUsuarioFromToken(String token) {
        String username = jwtService.extractUsername(token); // Extrae el nombre de usuario del token
        return userRepository.findTopByUsernameAndActivoTrue(username);
    }

    public Boolean validaUsernameEmailExistentes(User user) {
        Boolean existeEmail = userRepository.existsByEmail(user.getEmail());
        Boolean existeUsername = userRepository.existsByUsername(user.getUsername());

        if (existeEmail || existeUsername){

            return true;

        }else {

            return false;

        }

    }

}
