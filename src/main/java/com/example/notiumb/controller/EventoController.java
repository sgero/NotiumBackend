package com.example.notiumb.controller;

import com.example.notiumb.dto.*;
import com.example.notiumb.service.EventoService;
import com.example.notiumb.utilidades.RespuestaDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    public RespuestaDTO obtenerTodos(){
        return eventoService.getAll();
    }

    @ApiOperation(value = "Crear Evento", response = RespuestaDTO.class)
    @ApiResponses({@ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 500, message = "error interno del servidor")})
    @PostMapping(value = "/crear")
    public RespuestaDTO crear(@RequestBody CrearEventoDTO crearEventoDTO){
        return eventoService.crearEvento(crearEventoDTO.getEventoDTO(), crearEventoDTO.getEntradaOcioDTO(), crearEventoDTO.getReservadoOcioDTO(), crearEventoDTO.getListaOcioDTO());
    }
}
