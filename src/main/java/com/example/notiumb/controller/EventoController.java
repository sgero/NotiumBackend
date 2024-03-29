package com.example.notiumb.controller;

import com.example.notiumb.dto.EventoDTO;
import com.example.notiumb.service.EventoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/evento")
//@SecurityRequirement(name = "Bearer Authentication")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping("/todos")
    public List<EventoDTO> obtenerTodos(){
        return eventoService.getAll();
    }
}
