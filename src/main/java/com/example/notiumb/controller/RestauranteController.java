package com.example.notiumb.controller;

import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestauranteController {

    @GetMapping("/restaurantes")
    public List<User> getRestaurantes() {
        // implementación para obtener restaurantes

        return null;
    }


    @PostMapping("/restaurantes")
    public ResponseEntity<User> createRestaurante(@RequestBody Restaurante restaurante) {
        // implementación para crear un restaurante

        return null;
    }
}

