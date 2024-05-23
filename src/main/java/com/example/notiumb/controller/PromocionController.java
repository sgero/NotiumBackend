package com.example.notiumb.controller;

import com.example.notiumb.dto.PromocionDTO;
import com.example.notiumb.model.Promocion;
import com.example.notiumb.repository.IPromocionRepository;
import com.example.notiumb.service.PromocionService;
import com.example.notiumb.utilidades.RespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promocion")
public class PromocionController {
    @Autowired
    private PromocionService promocionService;
    @Autowired
    private IPromocionRepository promocionRepository;

    @GetMapping(value = "/{idPromocion}/verificarCodigo")
    public RespuestaDTO verificarCodigo(@PathVariable(value = "idPromocion") Integer idPromocion, @RequestParam String codigo){
        return promocionService.verificarCodigo(idPromocion, codigo);
    }

    @GetMapping
    public List<PromocionDTO> getActivas(){
        return promocionService.getActivas();
    }


}
