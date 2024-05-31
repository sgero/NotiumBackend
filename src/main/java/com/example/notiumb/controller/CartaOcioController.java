package com.example.notiumb.controller;

import com.example.notiumb.dto.CartaOcioDTO;
import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.model.CartaOcio;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.service.CartaOcioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartasOcio")
public class CartaOcioController {
    @Autowired
    private CartaOcioService service;

    @GetMapping("/listar")
    public List<CartaOcioDTO> listarCartas(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public CartaOcioDTO cartaId(@PathVariable Integer id){ return service.getById(id);}
    @GetMapping("/listarByOcio/{id}")
    public CartaOcioDTO cartabyOcio(@PathVariable(value = "id") Integer idOcio){ return service.getByOcioId(idOcio);}
    @PostMapping("/guardar/{id}")
    public CartaOcioDTO guardarCarta(@PathVariable(value = "id") Integer idOcio, @RequestBody CartaOcioDTO cartaOcioDTO){
        return service.save(idOcio, cartaOcioDTO);
    }
    @DeleteMapping("/{id}")
    public void eliminarCarta(@PathVariable(value = "id") Integer idOcio){ service.delete(idOcio); }
}
