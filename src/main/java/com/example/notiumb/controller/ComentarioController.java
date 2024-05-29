package com.example.notiumb.controller;

import com.example.notiumb.dto.ComentarioDTO;
import com.example.notiumb.dto.ComprobarCodigoDTO;
import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.repository.IComentarioRepository;
import com.example.notiumb.service.ComentarioService;
import com.example.notiumb.utilidades.RespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private IComentarioRepository comentarioRepository;

    @PostMapping(value="/comprobarCodigo")
    public Integer comprobarCodigo(@RequestBody ComprobarCodigoDTO info) {
        return comentarioService.comprobarValoracion(info);
    }

    @PostMapping(value="/crear")
    public RespuestaDTO crearValoracion(@RequestBody ComentarioDTO comentarioDTO){
        return comentarioService.crearValoracion(comentarioDTO);
    }

    @GetMapping(value="/rankingRestaurante")
    public List<Integer> rankingRestaurante(){
        return comentarioRepository.rankingRestaurantes();
    }

    @GetMapping(value="/rankingOcioNocturno")
    public List<Integer> rankingOcioNocturno(){
        return comentarioRepository.rankingOcioNocturno();
    }








}
