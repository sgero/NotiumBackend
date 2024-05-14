package com.example.notiumb.controller;

import com.example.notiumb.dto.ReservadoOcioDTO;
import com.example.notiumb.model.ReservadoOcio;
import com.example.notiumb.service.ReservadoOcioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservados")
public class ReservadoOcioController {

    @Autowired
    private ReservadoOcioService service;

    @GetMapping("/listar")
    public List<ReservadoOcioDTO> listarResevados(){ return service.getAll(); }

    @GetMapping("/{id}")
    public ReservadoOcioDTO reservadoId(@PathVariable Integer id){ return service.getById(id); }

    @DeleteMapping("/{id}")
    public void eliminarReservado(@PathVariable Integer id){ service.delete(id); }
}
