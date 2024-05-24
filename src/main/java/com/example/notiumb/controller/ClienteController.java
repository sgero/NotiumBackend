package com.example.notiumb.controller;

import com.example.notiumb.dto.ClienteDTO;
import com.example.notiumb.dto.UserClienteDTO;
import com.example.notiumb.model.User;
import com.example.notiumb.service.ClienteService;
import com.example.notiumb.utilidades.RespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/crear")
    public ClienteDTO crearYModificarCliente(@RequestBody UserClienteDTO userClienteDTO) {
        return clienteService.crearYModificarCliente(userClienteDTO);
    }

    @GetMapping("/{id}")
    public ClienteDTO getByUserId(@PathVariable Integer id){
        return clienteService.getByUserId(id);
    }

}
