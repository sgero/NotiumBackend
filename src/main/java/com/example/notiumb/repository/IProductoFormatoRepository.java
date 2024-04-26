package com.example.notiumb.repository;

import com.example.notiumb.model.ProductoFormato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoFormatoRepository extends JpaRepository<ProductoFormato, Integer> {
}
