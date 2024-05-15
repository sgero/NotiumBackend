package com.example.notiumb.controller;

import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.model.User;
import com.example.notiumb.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;

import java.util.List;

@RestController
@RequestMapping("/users")
//@Api(tags = "Gestión de Usuarios") >>> Estas son anotaciones antiguas de springfox que no se usan con springdoc
public class UserController {

    @Autowired
    private UserService userService;

//    @ApiOperation("Obtener todos los usuarios")
    @GetMapping("/listar")
    public List<UserDTO> getUsers() {

       return userService.listarUsers();
    }

//    @ApiOperation("Crear un nuevo usuario")
    @PostMapping("/crear")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // implementación para crear un usuario

        return null;
    }

//    @PostMapping("/registrar")
//    public ResponseEntity<User> registrarUsuario(@RequestBody User user) {
//        try {
//            User usuario = userService.registrarUsuario(user);
//            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
//        } catch (MessagingException e) {
//            // Manejar la excepción de mensajería (MessagingException) aquí si es necesario
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarUsuario(@RequestBody User user) {
        try {
            userService.registrarUsuario(user);
            return ResponseEntity.ok("Usuario registrado correctamente");
        } catch (MessagingException e) {
            // Manejar la excepción de mensajería (MessagingException) aquí si es necesario
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al enviar el correo de verificación");
        }
    }


    @PostMapping("/eliminar")
    public String deleteUser(@RequestBody User user) {
        
        return userService.deleteUser(user);

    }
}
