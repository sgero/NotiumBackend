package com.example.notiumb.security.service;

import com.example.notiumb.converter.IUserMapper;
import com.example.notiumb.dto.LoginDTO;
import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.model.User;
import com.example.notiumb.repository.IUserRepository;
import com.example.notiumb.security.auth.AuthenticationResponseDTO;
import com.example.notiumb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IUserRepository userRepository;


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
        User user = userRepository.findTopByUsername(loginDTO.getUsername());

        if (!user.getActivo()){

            return AuthenticationResponseDTO
                    .builder()
                    .token("")
                    .message("Su cuenta se ha dado de baja.")
                    .build();

        }

        if (!user.getVerificado()){

            return AuthenticationResponseDTO
                    .builder()
                    .token("")
                    .message("Tienes que verificar tu cuenta, mira el correo.")
                    .build();

        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword(),
                        List.of(new SimpleGrantedAuthority(user.getRol().name()))
                )
        );

        String token = jwtService.generateToken(user);
        return  AuthenticationResponseDTO
                .builder()
                .token(token)
                .message("Login success")
                .build();
    }

    public boolean verifyPassword(LoginDTO loginDTO){
        return userService.existByCredentials(loginDTO.getUsername(),loginDTO.getPassword());

    }

}