package com.example.notiumb.repository;

import com.example.notiumb.model.ListaOcioCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IListaOcioClienteRepository extends JpaRepository<ListaOcioCliente, Integer> {
}
