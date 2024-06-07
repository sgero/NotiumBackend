package com.example.notiumb.controller;

import com.example.notiumb.dto.ProductoDTO;
import com.example.notiumb.dto.ProductoFormatoDTO;
import com.example.notiumb.dto.TokenDTO;
import com.example.notiumb.model.User;
import com.example.notiumb.service.ProductoFormatoService;
import com.example.notiumb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/productoFormato")
public class ProductoFormatoController {

    @Autowired
    ProductoFormatoService productoFormatoService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/crear")
    public ProductoFormatoDTO crearProducto(@RequestBody ProductoFormatoDTO productoFormatoDTO) {
        return productoFormatoService.crearProductoFormato(productoFormatoDTO);
    }

    @PostMapping(value = "/listarTodos")
    public List<ProductoFormatoDTO> listarProductos(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        System.out.println("Token recibido: " + token);
        User user = userService.getUsuarioFromToken(token);
        return productoFormatoService.listarByFormato(user);
    }

    @PostMapping(value = "/listarTodosCliente")
    public List<ProductoFormatoDTO> listarProductos(@RequestBody TokenDTO tokenDTO) {
        return productoFormatoService.listarByFormatoCliente(tokenDTO);
    }

}
