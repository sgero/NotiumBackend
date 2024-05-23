package com.example.notiumb.security.auth;


import com.example.notiumb.dto.LoginDTO;
import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.model.User;
import com.example.notiumb.repository.IUserRepository;
import com.example.notiumb.security.service.AuthenticationService;
import com.example.notiumb.security.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private IUserRepository userRepository;

    @GetMapping("/getusuario")
    public User getUsername(@RequestHeader HashMap<String, String> headers){
        String key = "authorization";
        String localToken = headers.get(key);
        String token = localToken.substring(7);
        String username = jwtService.extractUsername(token);
        return userRepository.findTopByUsername(username);
    }


    @PostMapping("/registrocliente")
    public AuthenticationResponseDTO register(@RequestBody UserDTO userDTO){
        userDTO.setActivo(true);
        return  authenticationService.register(userDTO);
    }

    @PostMapping("/login")
    public AuthenticationResponseDTO register(@RequestBody LoginDTO loginDTO){
        if(authenticationService.verifyPassword(loginDTO)){
            return authenticationService.login(loginDTO);
        }else{
            return AuthenticationResponseDTO.builder().message("Invalid credentials").build();
        }
    }
}