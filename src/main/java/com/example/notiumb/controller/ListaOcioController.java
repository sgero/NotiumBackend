package com.example.notiumb.controller;

import com.example.notiumb.dto.CartaOcioDTO;
import com.example.notiumb.dto.ListaOcioDTO;
import com.example.notiumb.model.ListaOcio;
import com.example.notiumb.service.ListaOcioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listasOcio")
public class ListaOcioController {

    @Autowired
    private ListaOcioService service;

    @GetMapping("/listar")
    public List<ListaOcioDTO> listarListas(){ return service.getAll(); }

    @GetMapping("/{id}")
    public ListaOcioDTO listaId(@PathVariable Integer id){ return service.getById(id); }
    @GetMapping("/evento/{id}")
    public List<ListaOcioDTO> listaOcioByEvento(@PathVariable Integer id){ return service.getAllByEventoId(id); }
    @DeleteMapping("/{id}")
    public void eliminarLista(@PathVariable Integer id){ service.delete(id); }
}
