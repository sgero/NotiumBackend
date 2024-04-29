package com.example.notiumb.controller;

import com.example.notiumb.dto.*;
import com.example.notiumb.service.EventoService;
import com.example.notiumb.utilidades.RespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping("/listarTodos")
    public RespuestaDTO obtenerTodos(){
        return eventoService.getAll();
    }

    @PostMapping(value = "/crearUnico")
    public RespuestaDTO crearUnico(@RequestBody CrearEventoDTO crearEventoDTO){
        return eventoService.crearEventoUnico(crearEventoDTO.getEventoDTO(), crearEventoDTO.getEntradaOcioDTO(),
                crearEventoDTO.getReservadoOcioDTO(), crearEventoDTO.getListaOcioDTO());
    }

    @PostMapping(value = "/crearCiclo")
    public RespuestaDTO crearCiclo(@RequestBody CrearEventoCiclicoDTO crearEventoDTO){
        return eventoService.crearEventoCiclico(crearEventoDTO.getEventoDTO(), crearEventoDTO.getEntradaOcioDTO(),
                crearEventoDTO.getReservadoOcioDTO(), crearEventoDTO.getListaOcioDTO(), crearEventoDTO.getRepetirCicloEventoOcio(),
                crearEventoDTO.getDiasARepetirCicloEventoOcioList());
    }

    @PostMapping(value = "/eliminarEvento")
    public RespuestaDTO eliminarEvento(@RequestBody Integer id){
        return eventoService.eliminarEvento(id);
    }
}
