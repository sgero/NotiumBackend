package com.example.notiumb.service;


import com.example.notiumb.converter.*;
import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.model.User;
import com.example.notiumb.model.enums.Rol;
import com.example.notiumb.repository.*;
import com.example.notiumb.security.service.JWTService;
import com.example.notiumb.service.implementation.EmailServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.imageio.event.IIOReadProgressListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    private IClienteMapper clienteMapper;

    @Autowired
    private IRestauranteMapper restauranteMapper;

    @Autowired
    private IOcioNocturnoMapper ocioNocturnoMapper;

    @Autowired
    private IRppMapper rppMapper;

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

        User usuario = userRepository.findTopByUsername(user.getUsername());

        usuario.setActivo(false);
        userRepository.save(usuario);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario eliminado");

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(response);
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"message\":\"Error al procesar la solicitud\"}";
        }
    }

    public User obtenerUsuarioPorToken(String tokenVerificacion) {
        return userRepository.findTopByTokenVerificacion(tokenVerificacion);
    }



    public void actualizarUsuario(User usuario) {
        userRepository.save(usuario);
    }

    public Object traerPerfilVinculadoUsuario(User user) {
        if (user.getRol() == Rol.CLIENTE){

            return clienteMapper.toDTO(clienteRepository.findByUserId(user.getId()));

        } else if (user.getRol() == Rol.RESTAURANTE) {

            return restauranteMapper.toDTO(restauranteRepository.findByUserId(user.getId()));

        }else if (user.getRol() == Rol.OCIONOCTURNO) {

            return ocioNocturnoMapper.toDTO(ocioNocturnoRepository.findByIdUser(user.getId()));

        }else if (user.getRol() == Rol.RPP) {

            return rppMapper.toDTO(rppRepository.findByIdUser(user.getId()));

        }else if (user.getRol() == Rol.ADMIN) {

            return user;

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
        return existeEmail || existeUsername;
    }

}
