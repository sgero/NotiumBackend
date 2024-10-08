package com.example.notiumb.controller;

import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.User;
import com.example.notiumb.service.OcioNocturnoService;
import com.example.notiumb.service.RestauranteService;
import com.example.notiumb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verificar")
public class EmailVerificacionController {

    @Autowired
    private UserService userService;
    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private OcioNocturnoService ocioNocturnoService;

    @GetMapping("/cliente")
    @ResponseBody
    public String verificarEmailCliente(@RequestParam("token") String tokenVerificacion) {

        User usuario = userService.obtenerUsuarioPorToken(tokenVerificacion);

        if (usuario != null) {

            usuario.setVerificado(true);
            userService.actualizarUsuario(usuario);

//            model.addAttribute("mensajeTitulo", "¡Email verificado correctamente!");
//            model.addAttribute("mensajeCuerpo", "Tu email ha sido verificado exitosamente.");

        } else {
//            model.addAttribute("mensajeTitulo", "Error de Verificación");
//            model.addAttribute("mensajeCuerpo", "Token de verificación inválido o expirado. Por favor, verifica el link de verificación o solicita uno nuevo.");
        }

        return "verificacion-email";
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
        SimpleMailMessage mensaje = new SimpleMailMessage();
        String enlaceLogin = "http://localhost:8000/notium/login";

        if (restaurante != null) {
            // Marcar el usuario como verificado
            restaurante.setVerificado(true);
            restauranteService.actualizarRestaurante(restaurante);

            //Enviar email de verificacion al restaurante que se ha verificado
            mensaje.setTo(restaurante.getUser().getEmail());
            mensaje.setSubject("Enhorabuena, restaurante ha sido verificado");
            mensaje.setText("Enhorabuena, tu restaurante ha sido verificado. Ya puedes acceder a tu cuenta " + enlaceLogin);

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
        SimpleMailMessage mensaje = new SimpleMailMessage();
        String enlaceLogin = "http://localhost:8000/notium/login";

        if (ocioNocturno != null) {
            // Marcar el usuario como verificado
            ocioNocturno.setVerificado(true);
            ocioNocturnoService.actualizarOcioNocturno(ocioNocturno);

            //Enviar email de verificacion al ocio nocturno que se ha verificado
            mensaje.setTo(ocioNocturno.getUser().getEmail());
            mensaje.setSubject("Enhorabuena, tu local de ocio nocturno ha sido verificado");
            mensaje.setText("Enhorabuena, tu local de ocio nocturno ha sido verificado. Ya puedes acceder a tu cuenta " + enlaceLogin);




            return "¡Local de Ocio Nocturno verificado correctamente!";
        } else {
            return "Token de verificación inválido o expirado.";
        }
    }



}


