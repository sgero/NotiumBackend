package com.example.notiumb.controller;

import com.example.notiumb.dto.CartaOcioDTO;
import com.example.notiumb.dto.ListaOcioClienteDTO;
import com.example.notiumb.model.Cliente;
import com.example.notiumb.model.ListaOcioCliente;
import com.example.notiumb.service.EventoService;
import com.example.notiumb.service.ListaOcioClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listasClientes")
public class ListaOcioClienteController {

    @Autowired
    private ListaOcioClienteService service;
    @Autowired
    private EventoService eventoService;

    @GetMapping("/listar")
    public List<ListaOcioCliente> listarListasCliente(@RequestBody (required = true) Cliente cliente){
        return service.getListas(cliente.getId());
    }

//    @PostMapping("/guardar")
//    public ListaOcioClienteDTO guardarListaCliente(@RequestBody(required = true) Cliente cliente){
//
//    }
}
