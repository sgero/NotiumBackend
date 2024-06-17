package com.example.notiumb.controller;

import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.model.User;
import com.example.notiumb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/listar")
    public List<UserDTO> getUsers() {
       return userService.listarUsers();
    }

    @PostMapping("/eliminar")
    public String deleteUser(@RequestBody User user) {
        return userService.deleteUser(user);
    }

    @PostMapping("/activar")
    public String activarUser(@RequestBody User user) {
        return userService.activarUser(user);
    }

    @PostMapping("/validar")
    public Boolean validaUsernameEmailExistentes(@RequestBody User user) {
        return userService.validaUsernameEmailExistentes(user);
    }

    @PostMapping("/perfil")
    public Object traerPerfilVinculadoUsuario(@RequestBody User user) {
        return userService.traerPerfilVinculadoUsuario(user);
    }

}
