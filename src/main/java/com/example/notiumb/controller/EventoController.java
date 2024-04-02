package com.example.notiumb.controller;

import com.example.notiumb.dto.EntradaOcioDTO;
import com.example.notiumb.dto.EventoDTO;
import com.example.notiumb.dto.ListaOcioDTO;
import com.example.notiumb.dto.ReservadoOcioDTO;
import com.example.notiumb.service.EventoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    public EventoDTO crear(@RequestBody EventoDTO eventoDTO, EntradaOcioDTO entradaOcioDTO, ReservadoOcioDTO reservadoOcioDTO, List<ListaOcioDTO> listaOcioDTO){
        return eventoService.crearEvento(eventoDTO, entradaOcioDTO, reservadoOcioDTO, listaOcioDTO);
    }
}
