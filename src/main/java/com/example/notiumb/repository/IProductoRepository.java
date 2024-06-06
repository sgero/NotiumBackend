package com.example.notiumb.repository;

import com.example.notiumb.model.CartaOcio;
import com.example.notiumb.model.CartaRestaurante;
import com.example.notiumb.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByCartaResEqualsAndActivoTrue(CartaRestaurante cartaRes);
    List<Producto> findByCartaOcioEqualsAndActivoTrue(CartaOcio cartaOcio);

    Producto findTopById(Integer id);

    List<Producto> findByCartaResEqualsAndActivoFalse(CartaRestaurante cartaRes);

}
