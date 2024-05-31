package com.example.notiumb.controller;

import com.example.notiumb.dto.ProductoFormatoDTO;
import com.example.notiumb.service.ProductoFormatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/productoFormato")
public class ProductoFormatoController {

    @Autowired
    ProductoFormatoService productoFormatoService;

    @PostMapping(value = "/crear")
    public ProductoFormatoDTO crearProducto(@RequestBody ProductoFormatoDTO productoFormatoDTO) {
        return productoFormatoService.crearProductoFormato(productoFormatoDTO);
    }

}
