package com.example.notiumb.repository;

import com.example.notiumb.model.ProductoTipoPlato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoTipoPlatoRepository extends JpaRepository<ProductoTipoPlato, Integer> {
}
