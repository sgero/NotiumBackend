package com.example.notiumb.controller;

import com.example.notiumb.dto.EntradaOcioClienteDTO;
import com.example.notiumb.service.CompraClienteOcioService;
import com.example.notiumb.utilidades.RespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comprar")
public class CompraClienteOcioController {
    @Autowired
    private CompraClienteOcioService compraClienteOcioService;

    @PostMapping(value = "/{idEvento}/entrada/{idEntradaOcio}/{idCliente}")
    public RespuestaDTO comprarEntrada(@PathVariable(value = "idEvento") Integer idEvento,
                                       @PathVariable(value = "idCliente") Integer idCliente,
                                       @PathVariable(value = "idEntradaOcio") Integer idEntradaOcio,
                                       @RequestBody List<EntradaOcioClienteDTO> entradasOcioClienteLista){
        return compraClienteOcioService.comprarEntradaNormal(idEvento, idCliente, idEntradaOcio, entradasOcioClienteLista);
    }
}
