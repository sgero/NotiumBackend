package com.example.notiumb.controller;

import com.example.notiumb.dto.ReservaDTO;
import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping(value="/listar")
    public List<ReservaDTO> getReserva() {
        return reservaService.listarReserva();
    }
}
