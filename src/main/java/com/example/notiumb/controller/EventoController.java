package com.example.notiumb.controller;

import com.example.notiumb.dto.*;
import com.example.notiumb.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
//@SecurityRequirement(name = "Bearer Authentication")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping("/listarTodos")
    public List<EventoDTO> obtenerTodos(){
        return eventoService.getAll();
    }

    @PostMapping(value = "/crear")
    public EventoDTO crear(@RequestBody CrearEventoDTO crearEventoDTO){
        return eventoService.crearEvento(crearEventoDTO.getEventoDTO(), crearEventoDTO.getEntradaOcioDTO(), crearEventoDTO.getReservadoOcioDTO(), crearEventoDTO.getListaOcioDTO());
    }
}
