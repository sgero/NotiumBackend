package com.example.notiumb.controller;

import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.dto.RppDTO;
import com.example.notiumb.dto.UserRppDTO;
import com.example.notiumb.service.RppService;
import jakarta.mail.MessagingException;
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

    @GetMapping("/listarByOcio/{id}")
    public List<RppDTO> getByOcioId(@PathVariable(value = "id") Integer idOcio){
        return service.getAllByOcio(idOcio);}

    @GetMapping("/{id}")
    public RppDTO rppId(@PathVariable Integer id){ return service.getById(id); }

    @PostMapping("/guardar/{id}")
    public RppDTO guardarRpp(@PathVariable Integer id, @RequestBody UserRppDTO dto){ return service.saveRpp(id,dto); }

    @PostMapping(value="/verificar")
    public String verificarRpp(@RequestBody RppDTO rppDTO){
        return service.verificarRpp(rppDTO);
    }


    @DeleteMapping("/{id}")
    public void eliminarRpp(@PathVariable Integer id){ service.delete(id); }

}
