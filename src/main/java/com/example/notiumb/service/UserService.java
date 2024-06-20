package com.example.notiumb.service;


import com.example.notiumb.converter.*;
import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.model.*;
import com.example.notiumb.model.enums.Rol;
import com.example.notiumb.repository.*;
import com.example.notiumb.security.service.JWTService;
import com.example.notiumb.service.implementation.EmailServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private IRestauranteRepository restauranteRepository;

    @Autowired
    private IOcioNocturnoMapper ocioNocturnoMapper;

    @Autowired
    private IRppMapper rppMapper;

    @Autowired
    private IRppRepository rppRepository;

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

    public UserDTO save(UserDTO userDTO) {
        return iUserMapper.toDTO(userRepository.save(iUserMapper.toEntity(userDTO)));
    }

    public Boolean existByCredentials(String username, String password) {
        User user = userRepository.findTopByUsername(username);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    public String deleteUser(User user) {

        User usuario = userRepository.findTopByUsername(user.getUsername());
        if (user.getRol() == Rol.CLIENTE){

            Cliente cliente = clienteRepository.findByUserId(user.getId());
            cliente.setActivo(false);
            clienteRepository.save(cliente);

        } else if (user.getRol() == Rol.RESTAURANTE) {

            Restaurante restaurante = restauranteRepository.findByUserId(user.getId());
            restaurante.setActivo(false);
            restauranteRepository.save(restaurante);

        }else if (user.getRol() == Rol.OCIONOCTURNO) {

            OcioNocturno ocio = ocioNocturnoRepository.findByIdUser(user.getId());
            ocio.setActivo(false);
            ocioNocturnoRepository.save(ocio);

        }else if (user.getRol() == Rol.RPP) {

            Rpp rpp = rppRepository.findByIdUser(user.getId());
            rpp.setActivo(false);
            rppRepository.save(rpp);

        }

        usuario.setActivo(false);
        userRepository.save(usuario);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario dado de baja.");

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(response);
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"message\":\"Error al procesar la solicitud.\"}";
        }
    }

    public String activarUser(User user) {

        User usuario = userRepository.findTopByUsername(user.getUsername());
        if (user.getRol() == Rol.CLIENTE){

            Cliente cliente = clienteRepository.findByUserId(user.getId());
            cliente.setActivo(true);
            clienteRepository.save(cliente);

        } else if (user.getRol() == Rol.RESTAURANTE) {

            Restaurante restaurante = restauranteRepository.findByUserId(user.getId());
            restaurante.setActivo(true);
            restauranteRepository.save(restaurante);

        }else if (user.getRol() == Rol.OCIONOCTURNO) {

            OcioNocturno ocio = ocioNocturnoRepository.findByIdUser(user.getId());
            ocio.setActivo(true);
            ocioNocturnoRepository.save(ocio);

        }else if (user.getRol() == Rol.RPP) {

            Rpp rpp = rppRepository.findByIdUserSinActivo(user.getId());
            rpp.setActivo(true);
            rppRepository.save(rpp);

        }

        usuario.setActivo(true);
        userRepository.save(usuario);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario activado.");

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(response);
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"message\":\"Error al procesar la solicitud.\"}";
        }
    }

    public User obtenerUsuarioPorToken(String tokenVerificacion) {
        return userRepository.findTopByTokenVerificacion(tokenVerificacion);
    }

    public UserDTO editarUsuario(User user) {

        User usuario = userRepository.findTopById(user.getId());
        usuario.setUsername(user.getUsername());
        usuario.setEmail(user.getEmail());
        userRepository.save(usuario);
        return iUserMapper.toDTO(usuario);

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

    public User getUsuarioFromToken(String token) {
        Integer id = Integer.valueOf(jwtService.extractId(token));
        return userRepository.findTopById(id);
    }

    public Boolean validaUsernameEmailExistentes(User user) {
        Boolean existeEmail = userRepository.existsByEmail(user.getEmail());
        Boolean existeUsername = userRepository.existsByUsername(user.getUsername());
        return existeEmail || existeUsername;
    }

}
