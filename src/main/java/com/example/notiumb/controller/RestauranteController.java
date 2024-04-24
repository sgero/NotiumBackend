package com.example.notiumb.controller;

import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.User;
import com.example.notiumb.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;


    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping("/listar")
    public List<Restaurante> getRestaurantes() {
        return restauranteService.listarRestaurantes();
    }


    @PostMapping("/crear")
    public void crearRestaurante(@RequestBody RestauranteDTO restauranteDTO) {
        restauranteService.crearRestaurante(restauranteDTO);
    }
}

