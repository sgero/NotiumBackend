package com.example.notiumb.controller;

import com.example.notiumb.model.User;
import com.example.notiumb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;

import java.util.List;

@RestController
@RequestMapping("/api")
//@Api(tags = "Gestión de Usuarios")
public class UserController {

    @Autowired
    private UserService userService;

//    @ApiOperation("Obtener todos los usuarios")
    @GetMapping("/users")
    public List<User> getUsers() {
        // implementación para obtener usuarios

       return null;
    }

//    @ApiOperation("Crear un nuevo usuario")
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // implementación para crear un usuario

        return null;
    }
}
