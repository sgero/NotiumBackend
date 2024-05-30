package com.example.notiumb.controller;


import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.service.CartaRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cartaRestaurante")
public class CartaRestauranteController {

    @Autowired
    CartaRestauranteService cartaRestauranteService;

    @PostMapping(value = "/crear")
    public String crearCartaRes(@RequestBody UserDTO usuario) {
        return cartaRestauranteService.crearCartaRes(usuario);
    }
}
