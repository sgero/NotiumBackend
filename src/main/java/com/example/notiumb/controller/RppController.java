package com.example.notiumb.controller;

import com.example.notiumb.dto.ListaOcioDTO;
import com.example.notiumb.dto.RppDTO;
import com.example.notiumb.service.RppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rpps")
public class RppController {

    @Autowired
    private RppService service;

    @GetMapping("/listar")
    public List<RppDTO> listarRpps(){ return service.getAll(); }

    @GetMapping("/{id}")
    public RppDTO RppId(@PathVariable Integer id){ return service.getById(id); }

    @PostMapping("/guardar")
    public RppDTO saveRpp(@RequestBody RppDTO dto){ return service.save(dto); }

    @DeleteMapping("/{id}")
    public void eliminarRpp(@PathVariable Integer id){ service.delete(id); }

}
