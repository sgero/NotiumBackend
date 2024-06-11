package com.example.notiumb.controller;

import com.example.notiumb.dto.ListadoProductosDTO;
import com.example.notiumb.dto.ProductoAuxDTO;
import com.example.notiumb.dto.ProductoDTO;
import com.example.notiumb.dto.TokenDTO;
import com.example.notiumb.model.User;
import com.example.notiumb.security.auth.AuthenticationResponseDTO;
import com.example.notiumb.security.service.JWTService;
import com.example.notiumb.service.ProductoService;
import com.example.notiumb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/crear")
    public ProductoDTO crearProducto(@RequestBody ProductoAuxDTO productoAuxDTO) {
        return productoService.crearProducto(productoAuxDTO);
    }

    @PostMapping(value = "/guardar")
    public ProductoDTO guardarProducto(@RequestBody ProductoAuxDTO productoAuxDTO, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        User user = userService.getUsuarioFromToken(token);
        return productoService.guardarProducto(productoAuxDTO, user);
    }

    @GetMapping(value = "/{id}")
    public ProductoDTO productoId(@PathVariable Integer id) {
        return productoService.getById(id);
    }

    @PostMapping(value = "/listar")
    public List<ListadoProductosDTO> listarProductos(@RequestBody TokenDTO tokenDTO) {
        return productoService.listarProducto(tokenDTO);
    }

    @PostMapping(value = "/listarTodos")
    public List<ProductoDTO> listarProductos(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        User user = userService.getUsuarioFromToken(token);
        return productoService.listarByProducto(user);
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

    @DeleteMapping(value = "/eliminar/{id}")
    public void eliminarProducto(@PathVariable Integer id) {
        productoService.deleteProductoFormato(id);
    }

}
