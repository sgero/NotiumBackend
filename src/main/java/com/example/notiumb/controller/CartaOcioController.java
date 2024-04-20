package com.example.notiumb.controller;

import com.example.notiumb.dto.CartaOcioDTO;
import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.model.CartaOcio;
import com.example.notiumb.service.CartaOcioService;
import jakarta.persistence.criteria.CriteriaBuilder;
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
    public CartaOcio cartaId(@PathVariable Integer id){
        return service.getById(id);}
    @PostMapping("/guardar")
    public CartaOcioDTO guardarCarta(@RequestBody (required = true) CartaOcioDTO cartaOcioDTO,
                                     @RequestBody (required = false)OcioNocturnoDTO ocioNocturnoDTO){
        return service.save(cartaOcioDTO , ocioNocturnoDTO);}
    @DeleteMapping("/eliminar")
    public String eliminarCarta(@RequestBody CartaOcioDTO cartaOcioDTO){
        return service.delete(cartaOcioDTO);
    }
}
