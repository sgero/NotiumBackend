package com.example.notiumb.controller;

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

    @PostMapping(value = "/listar")
    public List<ProductoDTO> listarProductos(@RequestBody TokenDTO tokenDTO) {
        return productoService.listarProducto(tokenDTO);
    }
}
