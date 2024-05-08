package com.example.notiumb.controller;

import com.example.notiumb.service.CompraClienteOcioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compra")
public class CompraClienteOcioController {
    @Autowired
    private CompraClienteOcioService compraClienteOcioService;
}
