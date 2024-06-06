package com.example.notiumb.controller;

import com.example.notiumb.dto.*;
import com.example.notiumb.model.CartaOcio;
import com.example.notiumb.model.Reserva;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.service.CartaOcioService;
import com.example.notiumb.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @PostMapping(value = "/crear")
    public ReservaDTO crearReserva(@RequestBody DatosReservaDTO reservaDTO) {
        return service.crearReserva(reservaDTO);
    }

    @GetMapping("/listar")
    public List<ReservaDTO> getReservas() {
        return service.getAll();
    }

    @GetMapping(value = "/listarReservaRestaurante")
    public List<ReservaDTO> getReservaRestaurante(@RequestParam Integer id) {
        return service.getReservaPorRestaurante(id);
    }

    @GetMapping("/{id}")
    public Reserva getReservaById(@PathVariable Integer id) {
        return service.getReservaById(id);
    }

    @PostMapping("/eliminar/{id}")

    public void eliminarReserva(@PathVariable Integer id){ service.eliminarReserva(id); }


    @PostMapping("/comprobar")

    public void comprobarTurno(@RequestBody ReservaDTO reservaDTO){ service.comprobarTurno(reservaDTO); }

}
