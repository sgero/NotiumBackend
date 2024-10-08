package com.example.notiumb.controller;

import com.example.notiumb.dto.IdreceptorDTO;
import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.dto.UserRestauranteDTO;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.User;
import com.example.notiumb.service.ComentarioService;
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
    @Autowired
    private ComentarioService comentarioService;


    @GetMapping(value="/listar")
    public List<RestauranteDTO> getRestaurantes() {
        return restauranteService.listarRestaurantes();
    }

    @GetMapping(value = "/porID")
    public RestauranteDTO getRestauranteById(@RequestParam Integer id) {
        return restauranteService.getRestauranteByID(id);
    }

    @PostMapping(value="/crear")
    public RestauranteDTO crearYModificarRestaurante(@RequestBody UserRestauranteDTO userRestauranteDTO) throws MessagingException {
        return restauranteService.crearYModificarRestaurante(userRestauranteDTO);
    }

    @PostMapping(value="/verificar")
    public String verificarRestaurante(@RequestBody RestauranteDTO restauranteDTO) throws MessagingException {
        return restauranteService.verificarRestaurante(restauranteDTO);
    }

    @GetMapping(value="/notaMedia")
    public Double notaMedia(@RequestParam Integer id) {
        return comentarioService.valoracionMedia(id);
    }

    @PostMapping(value = "/listaCategoria")
    public List<RestauranteDTO> listarResCategoria(@RequestBody IdreceptorDTO idClase){
        Integer id = idClase.getId();
        return restauranteService.listarRestaurantesCategoria(id);
    }

    @GetMapping(value="/rankingRestaurante")
    public List<RestauranteDTO> rankingRestaurante(){
        return restauranteService.rankingRestaurante();
    }

    @GetMapping(value="/restauranteMasValorados")
    public List<RestauranteDTO> restauranteMasValorados(){
        return restauranteService.restaurantesMasValorados();
    }


}

