package com.example.notiumb.controller;

import com.example.notiumb.service.ListaOcioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/listasOcio")
public class ListaOcioController {

    @Autowired
    private ListaOcioService service;
}
