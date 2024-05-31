package com.example.notiumb.controller;

import com.example.notiumb.dto.ListadoProductosDTO;
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

    @PostMapping(value = "/listar")
    public List<ListadoProductosDTO> listarProductos(@RequestBody TokenDTO tokenDTO) {
        return productoService.listarProducto(tokenDTO);
    }

    @PutMapping(value = "/baja")
    public String crearProducto(@RequestBody ProductoDTO productoDTO) {
        return productoService.BajaAltaProducto(productoDTO);
    }

    @PostMapping(value = "/listardescarte")
    public List<ListadoProductosDTO> listarProductosDescarte(@RequestBody TokenDTO tokenDTO) {
        return productoService.listarProductoDescartado(tokenDTO);
    }

    @PostMapping(value = "/eliminarres")
    public String eliminarProducto(@RequestBody ProductoDTO productoDTO) {
        return productoService.eliminarProducto(productoDTO);
    }
}
