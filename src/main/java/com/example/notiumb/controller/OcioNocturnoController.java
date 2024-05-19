package com.example.notiumb.controller;

import com.example.notiumb.dto.CartaOcioDTO;
import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.model.CartaOcio;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.service.OcioNocturnoService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ocioNocturno")
public class OcioNocturnoController {

    @Autowired
    private OcioNocturnoService ocioNocturnoService;

    @GetMapping("/listar")
    public List<OcioNocturnoDTO> listarOcio(){ return ocioNocturnoService.getAll();}
    @GetMapping("/{id}")
    public OcioNocturno cartaId(@PathVariable Integer id){
        return ocioNocturnoService.getById(id);
    }
    @PostMapping("/guardar")
    public OcioNocturnoDTO guardarCarta(@RequestBody OcioNocturnoDTO ocioNocturnoDTO) throws MessagingException {
        return ocioNocturnoService.save(ocioNocturnoDTO);
    }
    @DeleteMapping("/{id}")
    public void eliminarCarta(@PathVariable Integer id){ ocioNocturnoService.delete(id); }
}
