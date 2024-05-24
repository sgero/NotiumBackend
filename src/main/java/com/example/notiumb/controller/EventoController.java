package com.example.notiumb.controller;

import com.example.notiumb.dto.CrearEventoCiclicoDTO;
import com.example.notiumb.dto.CrearEventoDTO;
import com.example.notiumb.dto.EventoDTO;
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
    public RespuestaDTO obtenerTodos(){ return eventoService.getAll();}

    @GetMapping("/listarByOcio/{id}")
    public RespuestaDTO getByOcioId(@PathVariable(value = "id") Integer idOcio){
        return eventoService.getAllByOcio(idOcio);}

    @GetMapping("/activos")
    public RespuestaDTO obtenerActivos(@RequestParam Integer numElem, @RequestParam Integer numPag){
        return eventoService.getActivos(numElem, numPag);
    }

    @GetMapping("/{id}")
    public RespuestaDTO getById(@PathVariable(value = "id") Integer idEvento){
        return eventoService.getById(idEvento);
    }
    @GetMapping("/{id}/entradas")
    public RespuestaDTO informacionEntradas(@PathVariable(value = "id") Integer idEvento){
        return eventoService.getInformacionEntradas(idEvento);
    }


    @GetMapping("/fechas/{id}")
    public RespuestaDTO fechasYOcio(@PathVariable(value = "id") Integer idOcio, @RequestParam Date fechaInicio,@RequestParam Date fechaFin){
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

    @GetMapping("/restante/{id}")
    public RespuestaDTO cantidadRestanteEntradas(@PathVariable Integer id){
        return eventoService.getCantidadRestanteEvento(id);
    }

}
