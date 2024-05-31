package com.example.notiumb.service;

import com.example.notiumb.converter.ICartaRestauranteMapper;
import com.example.notiumb.converter.IRestauranteMapper;
import com.example.notiumb.dto.*;
import com.example.notiumb.model.CartaRestaurante;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.User;
import com.example.notiumb.model.enums.TipoCategoria;
import com.example.notiumb.repository.ICartaRestauranteRespository;
import com.example.notiumb.repository.IRestauranteRepository;
import com.example.notiumb.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaRestauranteService {

    @Autowired
    private ICartaRestauranteRespository cartaRestauranteRespository;

    @Autowired
    private ICartaRestauranteMapper cartaRestauranteMapper;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRestauranteRepository restauranteRepository;

    @Autowired
    private IRestauranteMapper restauranteMapper;

    public String crearCartaRes(UserDTO usuario) {

        User user = userRepository.findTopByUsernameAndActivoTrue(usuario.getUsername());
        Restaurante restaurante = restauranteRepository.findTopByUserEquals(user);
        CartaRestaurante cartaexistente = cartaRestauranteRespository.findTopByRestauranteEquals(restaurante);
        if (cartaexistente != null) {
            // Lógica si la carta existe
            return "";
        } else {
            // Lógica si la carta no existe
            CartaRestaurante cartanueva = new CartaRestaurante();
            cartanueva.setRestaurante(restaurante);
            cartaRestauranteRespository.save(cartanueva);
        }



        return "Carta creada";

    }
}

