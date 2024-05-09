package com.example.notiumb.controller;

import com.example.notiumb.dto.ComentarioDTO;
import com.example.notiumb.dto.ComprobarCodigoDTO;
import com.example.notiumb.service.ComentarioService;
import com.example.notiumb.utilidades.RespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping(value="/comprobarCodigo")
    public Boolean comprobarCodigo(@RequestBody ComprobarCodigoDTO info) {
        return comentarioService.comprobarValoracion(info);
    }

    @PostMapping(value="/crear")
    public RespuestaDTO crearValoracion(@RequestBody ComentarioDTO comentarioDTO){
        return comentarioService.crearValoracion(comentarioDTO);
    }




}
