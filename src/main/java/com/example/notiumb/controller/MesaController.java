package com.example.notiumb.controller;

import com.example.notiumb.dto.MesaDTO;
import com.example.notiumb.model.Mesa;
import com.example.notiumb.service.MesaService;
import com.example.notiumb.utilidades.RespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/mesa")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @PostMapping(value="/crear")
    public RespuestaDTO crearMesa(@RequestBody MesaDTO mesaDTO){
        return mesaService.mesaRestaurante(mesaDTO);
    }

    @PostMapping(value="/eliminar")
    public RespuestaDTO eliminarMesa(@RequestParam Integer id){
        return mesaService.eliminarMesa(id);
    }

    @GetMapping(value= "/listar")
    public List<MesaDTO> listarMesas(){
        return mesaService.listadoMesas();
    }
}
