package com.example.notiumb.controller;

import com.example.notiumb.service.PromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promocion")
public class PromocionController {
    @Autowired
    private PromocionService promocionService;

    @GetMapping(value = "/{idPromocion}/verificarCodigo")
    public boolean verificarCodigo(@PathVariable(value = "idPromocion") Integer idPromocion, @RequestParam String codigo){
        return promocionService.verificarCodigo(idPromocion, codigo);
    }


}
