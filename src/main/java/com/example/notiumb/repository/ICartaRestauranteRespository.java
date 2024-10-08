package com.example.notiumb.repository;

import com.example.notiumb.model.CartaRestaurante;
import com.example.notiumb.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartaRestauranteRespository extends JpaRepository<CartaRestaurante, Integer> {

    CartaRestaurante findTopByRestauranteEquals(Restaurante restaurante);


}