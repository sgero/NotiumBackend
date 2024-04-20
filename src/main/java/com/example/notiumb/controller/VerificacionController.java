package com.example.notiumb.controller;

import com.example.notiumb.model.User;
import com.example.notiumb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verificar")
public class VerificacionController {
    @Autowired
    private UserService usuarioService;

    @GetMapping
    public ResponseEntity<String> verificarCuenta(@RequestParam("token") String token) {
        // Lógica para verificar el token y cambiar el campo verificado del usuario
        // Validar el token
        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body("El token de verificación no es válido.");
        }

        // Buscar el usuario por el token
        User usuario = usuarioService.obtenerUsuarioPorToken(token);
        if (usuario != null) {
            // Si se encuentra el usuario, marcarlo como verificado
            usuario.setVerificado(true);
            usuarioService.actualizarUsuario(usuario);
            return ResponseEntity.ok("Cuenta verificada exitosamente.");
        } else {
            // Si no se encuentra el usuario, devolver una respuesta de error
            return ResponseEntity.badRequest().body("El token de verificación no es válido.");
        }
}
}


