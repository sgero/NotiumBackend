package com.example.notiumb.security.auth;


import com.example.notiumb.dto.LoginDTO;
import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.security.service.AuthenticationService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Getter
    public static class LoginRequest {
        private String username;
        private String password;

        // Getters y Setters
    }


    public static class LoginResponse {
        public LoginResponse(String role) {
        }
        // Getters y Setters
    }


    @PostMapping("/registrocliente")
    public AuthenticationResponseDTO register(@RequestBody UserDTO userDTO){
        userDTO.setActivo(true);
        return  authenticationService.register(userDTO);
    }

    @PostMapping("/login")
    public AuthenticationResponseDTO login(@RequestBody LoginDTO loginDTO) {
        if (authenticationService.verifyPassword(loginDTO)) {
            return authenticationService.login(loginDTO);
        } else {
            return AuthenticationResponseDTO.builder().message("Invalid credentials").build();
        }
    }

//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        // Lógica para autenticar al usuario y obtener su rol
//        String role = authenticationService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
//
//        if (role != null) {
//            return ResponseEntity.ok(new LoginResponse(role));
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }




}