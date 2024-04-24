package com.example.notiumb.service;

import com.example.notiumb.dto.CartaRestauranteDTO;
import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.model.CartaRestaurante;
import com.example.notiumb.repository.ICartaRestauranteRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaRestauranteService {

    @Autowired
    private ICartaRestauranteRespository cartaRestauranteRespository;

    }

