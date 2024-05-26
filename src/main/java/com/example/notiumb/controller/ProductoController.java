package com.example.notiumb.controller;

import com.example.notiumb.dto.ProductoAuxDTO;
import com.example.notiumb.dto.ProductoDTO;
import com.example.notiumb.dto.TokenDTO;
import com.example.notiumb.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping(value = "/crear")
    public ProductoDTO crearProducto(@RequestBody ProductoAuxDTO productoAuxDTO) {
        return productoService.crearProducto(productoAuxDTO);
    }

    @PostMapping(value = "/guardar")
    public ProductoDTO guardarProducto(@RequestBody ProductoAuxDTO productoAuxDTO) {
        return productoService.guardarProducto(productoAuxDTO);
    }

    @PostMapping(value = "/listar")
    public List<ProductoDTO> listarProductos(@RequestBody TokenDTO tokenDTO) {
        return productoService.listarProducto(tokenDTO);
    }

//    @PutMapping(value = "/baja")
//    public String crearProducto(@RequestBody ProductoDTO productoDTO) {
//        return productoService.BajaAltaProducto(productoDTO);
//    }
}
