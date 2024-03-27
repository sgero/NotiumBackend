package com.example.notiumb.controller;

import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.model.User;
import com.example.notiumb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
