package com.example.notiumb.controller;

import com.example.notiumb.dto.CrearEventoCiclicoDTO;
import com.example.notiumb.dto.CrearEventoDTO;
import com.example.notiumb.service.EventoService;
import com.example.notiumb.utilidades.RespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping("/listarTodos")
    public RespuestaDTO obtenerTodos(){
        return eventoService.getAll();
    }

    @GetMapping("/activos")
    public RespuestaDTO obtenerActivos(){
        return eventoService.getActivos();
    }

    @PostMapping("/fechasYOcio")
    public RespuestaDTO fechasYOcio(@PathVariable(value = "Id del ocio nocturno") Integer idOcio, @RequestParam Date fechaInicio,@RequestBody Date fechaFin){
        return eventoService.eventosEntreFechasDeOcio(idOcio, fechaInicio, fechaFin);
    }

    @GetMapping("/fechas")
    public RespuestaDTO fechas(@RequestParam Date fechaInicio, @RequestParam Date fechaFin){
        return eventoService.eventosEntreFechas(fechaInicio, fechaFin);
    }

    @PostMapping(value = "/guardar")
    public RespuestaDTO guardarEvento(@RequestBody CrearEventoDTO crearEventoDTO){
        return eventoService.guardarEvento(crearEventoDTO.getEventoDTO(), crearEventoDTO.getEntradaOcioDTO(),
                crearEventoDTO.getReservadoOcioDTO(), crearEventoDTO.getListaOcioDTO());
    }

    @PostMapping(value = "/crearCiclo")
    public RespuestaDTO crearCiclo(@RequestBody CrearEventoCiclicoDTO crearEventoDTO){
        return eventoService.crearEventoCiclico(crearEventoDTO.getEventoDTO(), crearEventoDTO.getEntradaOcioDTO(),
                crearEventoDTO.getReservadoOcioDTO(), crearEventoDTO.getListaOcioDTO(), crearEventoDTO.getRepetirCicloEventoOcio(),
                crearEventoDTO.getDiasARepetirCicloEventoOcioList());
    }

    @PostMapping(value = "/eliminar")
    public RespuestaDTO eliminarEvento(@RequestParam Integer id){
        return eventoService.eliminarEvento(id);
    }
}
