package com.example.notiumb.repository;

import com.example.notiumb.model.ReservadoOcio;
import com.example.notiumb.model.ReservadoOcioCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservadoOcioClienteRepository extends JpaRepository <ReservadoOcioCliente, Integer> {
}
