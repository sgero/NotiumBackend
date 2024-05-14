package com.example.notiumb.controller;

import com.example.notiumb.dto.ClienteDTO;
import com.example.notiumb.dto.UserClienteDTO;
import com.example.notiumb.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/crear")
    public ClienteDTO crearYModificarCliente(@RequestBody UserClienteDTO userClienteDTO) {
        return clienteService.crearYModificarCliente(userClienteDTO);
    }

}
