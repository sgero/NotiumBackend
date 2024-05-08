package com.example.notiumb.controller;

import com.example.notiumb.dto.TurnoDTO;
import com.example.notiumb.model.Turno;
import com.example.notiumb.service.TurnoService;
import com.example.notiumb.utilidades.RespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/turno")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

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


}
