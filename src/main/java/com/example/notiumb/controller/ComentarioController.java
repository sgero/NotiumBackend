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

    @PostMapping(value="/crearRestaurante")
    public RespuestaDTO crearValoracionRestaurante(@RequestBody ComentarioDTO info) {
        return comentarioService.crearValoracion(info);
    }

    @GetMapping(value="/rankingRestaurante")
    public List<Integer> rankingRestaurante(){
        return comentarioService.rankingRestaurante();
    }

    @GetMapping(value="/comentarioPorRestaurante")
    public List<ComentarioDTO> comentarioRestaurante(@RequestParam Integer id){
        return comentarioService.valoracionPorRestaurante(id);
    }

    @GetMapping(value="/clientesPorComentario")
    public List<ClienteDTO> clientesComentario(@RequestParam Integer id){
        return comentarioService.clienteValorciones(id);
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

    @GetMapping(value="/listarValoracionesPorOcio")
    public List<ComentarioDTO> valoracionesPorOcio(@RequestParam Integer id){
        return comentarioService.valoracionesOcio(id);
    }









}
