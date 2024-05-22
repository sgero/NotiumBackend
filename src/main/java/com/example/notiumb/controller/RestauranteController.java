package com.example.notiumb.controller;

import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.User;
import com.example.notiumb.service.RestauranteService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;


    @GetMapping(value="/listar")
    public List<RestauranteDTO> getRestaurantes() {
        return restauranteService.listarRestaurantes();
    }

    @GetMapping(value = "/porID")
    public RestauranteDTO getRestauranteById(@RequestParam Integer id) {
        return restauranteService.getRestauranteByID(id);
    }


    @PostMapping(value="/crear")
    public Restaurante crearRestaurante(@RequestBody RestauranteDTO restauranteDTO) throws MessagingException {
        return restauranteService.crearRestaurante(restauranteDTO);
    }
}

