package com.example.notiumb.controller;

import com.example.notiumb.dto.ClienteDTO;
import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.dto.UserOcioNocturnoDTO;
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
    public OcioNocturnoDTO ocioId(@PathVariable Integer id){
        return ocioNocturnoService.getById(id);
    }

    @PostMapping("/crear")
    public OcioNocturnoDTO crearYModificarOcioNocturno(@RequestBody UserOcioNocturnoDTO userOcioNocturnoDTO) throws MessagingException {
        return ocioNocturnoService.crearYModificarOcioNocturno(userOcioNocturnoDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarOcio(@PathVariable Integer id){ ocioNocturnoService.delete(id); }

    @GetMapping("/usuario/{id}")
    public OcioNocturnoDTO getByUserId(@PathVariable Integer id){
        return ocioNocturnoService.getByUserId(id);
    }
}
