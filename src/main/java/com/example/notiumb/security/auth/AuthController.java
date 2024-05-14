package com.example.notiumb.security.auth;


import com.example.notiumb.dto.LoginDTO;
import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/register")
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