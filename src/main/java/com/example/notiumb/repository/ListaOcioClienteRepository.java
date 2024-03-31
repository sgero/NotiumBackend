package com.example.notiumb.repository;

import com.example.notiumb.model.ListaOcioCliente;
import com.example.notiumb.model.ReservadoOcio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaOcioClienteRepository extends JpaRepository<ListaOcioCliente, Integer> {
}
