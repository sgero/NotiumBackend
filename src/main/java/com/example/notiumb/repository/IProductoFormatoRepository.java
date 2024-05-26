package com.example.notiumb.repository;

import com.example.notiumb.model.Formato;
import com.example.notiumb.model.Producto;
import com.example.notiumb.model.ProductoFormato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoFormatoRepository extends JpaRepository<ProductoFormato, Integer> {

    List<ProductoFormato> findByProductoEquals(Producto producto);

    List<ProductoFormato> findByProductoEqualsAndFormatoEquals(Producto producto, Formato formato);

}
