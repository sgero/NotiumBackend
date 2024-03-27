package com.example.notiumb.repository;

import com.example.notiumb.model.EntradaOcioCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEntradaOcioCliente extends JpaRepository<EntradaOcioCliente, Integer> {
}
