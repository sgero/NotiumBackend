package com.example.notiumb.repository;

import com.example.notiumb.model.CartaRestaurante;
import com.example.notiumb.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByCartaResEqualsAndActivoTrue(CartaRestaurante cartaRes);
}
