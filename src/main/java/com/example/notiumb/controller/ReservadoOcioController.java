package com.example.notiumb.controller;

import com.example.notiumb.service.ReservadoOcioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservados")
public class ReservadoOcioController {

    @Autowired
    private ReservadoOcioService service;

}
