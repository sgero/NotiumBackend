package com.example.notiumb.security.service;

import com.example.notiumb.converter.IUserMapper;
import com.example.notiumb.dto.LoginDTO;
import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.security.auth.AuthenticationResponseDTO;
import com.example.notiumb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserService userService;

    private final IUserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;


    public AuthenticationResponseDTO register(UserDTO userDTO){
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserDTO dto = userService.save(userDTO);
        String token = jwtService.generateToken(userMapper.toEntity(dto));
        return AuthenticationResponseDTO
                .builder()
                .token(token)
                .build();
    }

    public AuthenticationResponseDTO login(LoginDTO loginDTO){
        UserDTO user = userService.getByUsername(loginDTO.getUsername());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword(),
                        List.of(new SimpleGrantedAuthority(user.getRol().name()))
                )
        );
        String token = jwtService.generateToken(userMapper.toEntity(user));
        return  AuthenticationResponseDTO
                .builder()
                .token(token)
                .message("Login success")
                .build();
    }

    public boolean verifyPassword(LoginDTO loginDTO){
        return userService.existByCredentials(loginDTO.getUsername(),loginDTO.getPassword());

    }

//    public String authenticateUser(String username, String password) {
//        // Implementar la lógica de autenticación y retornar el rol
//        // Integrate database access here
//        String role = null;
//        // Execute query to retrieve role based on username and password
//        // Validate retrieved role
//        if ("ADMIN".equals(username) && "password".equals(password)) {
//            role = "ADMIN";
//        } else if ("CLIENTE".equals(username) && "password".equals(password)) {
//            role = "CLIENTE";
//        } else if ("RESTAURANTE".equals(username) && "password".equals(password)) {
//            role = "RESTAURANTE";
//        } else if ("OCIONOCTURNO".equals(username) && "password".equals(password)) {
//            role = "OCIONOCTURNO";
//        } else if ("RPP".equals(username) && "password".equals(password)) {
//            role = "RPP";
//        }
//        // Return the retrieved role or null if not found
//        return role;
//    }


}