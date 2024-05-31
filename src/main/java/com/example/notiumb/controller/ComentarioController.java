package com.example.notiumb.controller;

import com.example.notiumb.dto.*;

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

    @PostMapping(value="/comprobarCodigoRestaurante")
    public Integer comprobarCodigoRestaurante(@RequestBody ComprobarCodigoRestauranteDTO info) {
        return comentarioService.comprobarValoracionRestaurante(info);
    }

    @GetMapping(value="/rankingRestaurante")
    public List<Integer> rankingRestaurante(){
        return comentarioService.rankingRestaurante();
    }

    @PostMapping(value="/comprobarCodigoOcioNocturno")
    public Integer comprobarCodigoOcioNocturno(@RequestBody ComprobarCodigoOcioDTO info) {
        return comentarioService.comprobarValoracionOcioNocturno(info);
    }

    @PostMapping(value="/crearOcioNocturno")
    public RespuestaDTO crearValoracionOcioNocturno(@RequestBody ComentarioDTO comentarioDTO){
        return comentarioService.crearValoracionOcioNocturno(comentarioDTO);
    }

    @GetMapping(value="/rankingOcioNocturno")
    public List<Integer> rankingOcioNocturno(){
        return comentarioService.rankingOcioNocturno();
    }








}
