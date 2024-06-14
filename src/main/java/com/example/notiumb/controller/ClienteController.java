package com.example.notiumb.controller;

import com.example.notiumb.dto.ClienteDTO;
import com.example.notiumb.dto.UserClienteDTO;
import com.example.notiumb.model.User;
import com.example.notiumb.service.ClienteService;
import com.example.notiumb.utilidades.RespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping()
    public List<ClienteDTO> getClientes(){
        return clienteService.getClientes();
    }

    @PostMapping("/crear")
    public ClienteDTO crearYModificarCliente(@RequestBody UserClienteDTO userClienteDTO) {
        return clienteService.crearYModificarCliente(userClienteDTO);
    }

    @GetMapping("/{id}")
    public ClienteDTO getByUserId(@PathVariable Integer id){
        return clienteService.getByUserId(id);
    }

    @GetMapping("delete/{id}")
    public ClienteDTO deleteCliente(@PathVariable Integer id){
        return clienteService.deleteCliente(id);
    }

}
