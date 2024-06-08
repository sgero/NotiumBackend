package com.example.notiumb.repository;

import com.example.notiumb.model.DatosComprador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDatosCompradorRepository extends JpaRepository<DatosComprador, Integer> {
    List<DatosComprador> findAllByReservadoOcioCliente_Id (Integer id);
}
