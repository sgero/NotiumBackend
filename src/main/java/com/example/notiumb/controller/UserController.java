package com.example.notiumb.controller;

import com.example.notiumb.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = "Gestión de Usuarios", description = "Operaciones relacionadas con la gestión de usuarios")
public class UserController {

    @ApiOperation("Obtener todos los usuarios")
    @GetMapping("/users")
    public List<User> getUsers() {
        // implementación para obtener usuarios

       return null;
    }

    @ApiOperation("Crear un nuevo usuario")
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // implementación para crear un usuario

        return null;
    }
}
