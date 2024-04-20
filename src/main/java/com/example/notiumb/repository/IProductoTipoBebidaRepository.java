package com.example.notiumb.repository;

import com.example.notiumb.model.ProductoTipoBebida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoTipoBebidaRepository extends JpaRepository<ProductoTipoBebida, Integer> {
}
