package com.example.notiumb.controller;

import com.example.notiumb.dto.DisponibilidadTurnoDTO;
import com.example.notiumb.dto.TurnoDTO;
import com.example.notiumb.model.Reserva;
import com.example.notiumb.model.Turno;
import com.example.notiumb.service.ReservaService;
import com.example.notiumb.service.TurnoService;
import com.example.notiumb.utilidades.RespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/turno")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private ReservaService reservaService;

    @PostMapping(value="/crear")
    public RespuestaDTO crearTurno(@RequestBody TurnoDTO turnoDTO) {
        return turnoService.crearTurno(turnoDTO);
    }

    @PostMapping(value="/eliminar")
    public RespuestaDTO eliminarTurno(@RequestParam Integer id){
        return turnoService.eliminarTurno(id);
    }

    @GetMapping(value="/listar")
    public List<TurnoDTO> listarTurnos(){
        return turnoService.listarTurnos();
    }


    @PostMapping("/disponibilidad")
    public List<TurnoDTO> comprobarTurnosDisponibles(@RequestBody DisponibilidadTurnoDTO disponibilidadRequest) {
        return reservaService.comprobarTurnosDisponibles(
                disponibilidadRequest.getNumPersonas(),
                disponibilidadRequest.getFecha(),
                disponibilidadRequest.getRestauranteId()
        );
    }

    @GetMapping("/disponibilidadTurnos")
    public List<TurnoDTO> turnosDisponibles(@RequestParam Integer id){
        return turnoService.turnosRestaurante(id);
    }



}
