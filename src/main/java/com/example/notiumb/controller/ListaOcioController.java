package com.example.notiumb.controller;

import com.example.notiumb.dto.ListaOcioDTO;
import com.example.notiumb.service.ListaOcioService;
import com.example.notiumb.thread.ListasOcioThread;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/listasOcio")
public class ListaOcioController {

    @Autowired
    private ListaOcioService service;
    private final ListasOcioThread listasOcioThread;

    @GetMapping("/listar")
    public List<ListaOcioDTO> listarListas(){ return service.getAll(); }

    @GetMapping("/rpp/{id}")
    public List<ListaOcioDTO> getByOcioId(@PathVariable(value = "id") Integer idRpp){
        return service.getAllByRpp(idRpp);}
    @GetMapping("/{id}")
    public ListaOcioDTO listaId(@PathVariable(value = "id") Integer id){ return service.getById(id); }
    @GetMapping("/evento/{id}")
    public ListaOcioDTO listaOcioActivaByEvento(@PathVariable Integer id){ return service.getActivaByEventoId(id); }

    @GetMapping("/evento/{idEvento}/actualizar/{idLista}")
    public ListaOcioDTO actualizarEstadoListas(@PathVariable(value = "idEvento") Integer idEvento, @PathVariable(value = "idLista") Integer idLista){
        return service.actualizarEstadoListas(idEvento, idLista);
    }
    @DeleteMapping("/{id}")
    public void eliminarLista(@PathVariable Integer id){ service.delete(id); }

    @PostMapping("/reasignar")
    public ResponseEntity<Void> reasignaListas(@RequestParam("idRppOriginal") Integer idRppOriginal,
                                               @RequestParam("idRppDestino") Integer idRppDestino){
        listasOcioThread.run(idRppOriginal, idRppDestino);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
