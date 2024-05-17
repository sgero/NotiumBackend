package com.example.notiumb.controller;

import com.example.notiumb.dto.*;
import com.example.notiumb.model.DatosComprador;
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

    @PostMapping(value = "/{idEvento}/entrada/{idEntradaOcio}")
    public RespuestaDTO comprarEntrada(@PathVariable(value = "idEvento") Integer idEvento,
                                       @PathVariable(value = "idEntradaOcio") Integer idEntradaOcio,
                                       @RequestParam Integer idCliente,
                                       @RequestBody List<EntradaOcioClienteDTO> entradasOcioClienteLista){
        return compraClienteOcioService.comprarEntradaNormal(idEvento, idCliente, idEntradaOcio, entradasOcioClienteLista);
    }

    @PostMapping(value = "/{idEvento}/reservado/{idReservadoOcio}")
    public RespuestaDTO comprarReservado(@PathVariable(value = "idEvento") Integer idEvento,
                                         @PathVariable(value = "idReservadoOcio") Integer idReservadoOcio,
                                         @RequestParam Integer idCliente,
                                         @RequestBody ComprarReservadoDTO comprarReservadoDTO){
        return compraClienteOcioService.comprarReservado(idEvento, idCliente, idReservadoOcio, comprarReservadoDTO.getReservadoOcioClienteDTO(), comprarReservadoDTO.getDatosCompradorDTOS());
    }

    @PostMapping(value = "/{idEvento}/lista/{idListaOcio}")
    public RespuestaDTO comprarLista(@PathVariable(value = "idEvento") Integer idEvento,
                                       @PathVariable(value = "idListaOcio") Integer idListaOcio,
                                       @RequestParam Integer idCliente,
                                       @RequestBody List<ListaOcioClienteDTO> listaOcioClienteDTOS){
        return compraClienteOcioService.comprarEntradaLista(idEvento, idCliente, idListaOcio, listaOcioClienteDTOS);
    }

}
