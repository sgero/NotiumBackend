package com.example.notiumb.controller;

import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.User;
import com.example.notiumb.service.OcioNocturnoService;
import com.example.notiumb.service.RestauranteService;
import com.example.notiumb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verificar")
public class EmailVerificacionController {
    @Autowired
    private UserService usuarioService;


@Autowired
private UserService userService;
@Autowired
private RestauranteService restauranteService;
@Autowired
private OcioNocturnoService ocioNocturnoService;

    @GetMapping("/cliente")
    public String verificarEmailCliente(@RequestParam("token") String tokenVerificacion) {
        // Obtener el usuario asociado al token
        User usuario = userService.obtenerUsuarioPorToken(tokenVerificacion);

        if (usuario != null) {
            // Marcar el usuario como verificado
            usuario.setVerificado(true);
            userService.actualizarUsuario(usuario);
            return "¡Email verificado correctamente!";
        } else {
            return "Token de verificación inválido o expirado.";
        }
    }


    @GetMapping("/continuarRegistroRestaurante")
    public String verificarEmailRestaurante(@RequestParam("token") String tokenVerificacion) {
        // Obtener el usuario asociado al token
        User usuario = userService.obtenerUsuarioPorToken(tokenVerificacion);

        if (usuario != null) {
            // Marcar el usuario como verificado
            usuario.setVerificado(true);
            userService.actualizarUsuario(usuario);
            return "¡Email verificado correctamente!";
        } else {
            return "Token de verificación inválido o expirado.";
        }
    }


    @GetMapping("/continuarRegistroOcioNocturno")
    public String verificarEmailOcioNocturno(@RequestParam("token") String tokenVerificacion) {
        // Obtener el usuario asociado al token
        User usuario = userService.obtenerUsuarioPorToken(tokenVerificacion);

        if (usuario != null) {
            // Marcar el usuario como verificado
            usuario.setVerificado(true);
            userService.actualizarUsuario(usuario);
            return "¡Email verificado correctamente!";
        } else {
            return "Token de verificación inválido o expirado.";
        }
    }


    //ESTE METODO VERIFICA RESTAURANTE (LA COLUMNA DE VERIFICADO DENTRO DE LA CLASE RESTAURANTE)
    @GetMapping("verificarRestaurante")
    public String getVerificarRestaurante(@RequestParam("idEmpresa") String cif) {
        // Obtener el usuario asociado al token
        Restaurante restaurante = restauranteService.getRestauranteByCif(cif);

        if (restaurante != null) {
            // Marcar el usuario como verificado
            restaurante.setVerificado(true);
            restauranteService.actualizarRestaurante(restaurante);
            return "¡Restaurante verificado correctamente!";
        } else {
            return "Token de verificación inválido o expirado.";
        }
    }


//ESTE METODO VERIFICA OCIONOCTURNO (LA COLUMNA DE VERIFICADO DENTRO DE LA CLASE OCIONOCTURNO)
    @GetMapping("verificarOcioNocturno")
    public String getVerificarOcioNocturno(@RequestParam("idEmpresa") String cif) {
        // Obtener el usuario asociado al token
        OcioNocturno ocioNocturno = ocioNocturnoService.getOcioNocturnoByCif(cif);

        if (ocioNocturno != null) {
            // Marcar el usuario como verificado
            ocioNocturno.setVerificado(true);
            ocioNocturnoService.actualizarOcioNocturno(ocioNocturno);
            return "¡Restaurante verificado correctamente!";
        } else {
            return "Token de verificación inválido o expirado.";
        }
    }

    //    @GetMapping
//    public ResponseEntity<String> verificarCuenta(@RequestParam("token") String token) {
//        // Lógica para verificar el token y cambiar el campo verificado del usuario
//        // Validar el token
//        if (token == null || token.isEmpty()) {
//            return ResponseEntity.badRequest().body("El token de verificación no es válido.");
//        }
//
//        // Buscar el usuario por el token
//        User usuario = usuarioService.obtenerUsuarioPorToken(token);
//        if (usuario != null) {
//            // Si se encuentra el usuario, marcarlo como verificado
//            usuario.setVerificado(true);
//            usuarioService.actualizarUsuario(usuario);
//            return ResponseEntity.ok("Cuenta verificada exitosamente.");
//        } else {
//            // Si no se encuentra el usuario, devolver una respuesta de error
//            return ResponseEntity.badRequest().body("El token de verificación no es válido.");
//        }
//}



//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/verificar")
//    public String verificarEmail(@RequestParam("token") String token) {
//        // Obtener el usuario asociado al token
//        User usuario = userService.obtenerUsuarioPorToken(token);
//
//        if (usuario != null) {
//            // Marcar el usuario como verificado
//            usuario.setVerificado(true);
//            userService.actualizarUsuario(usuario);
//            return "¡Email verificado correctamente!";
//        } else {
//            return "Token de verificación inválido o expirado.";
//        }
//    }

}


