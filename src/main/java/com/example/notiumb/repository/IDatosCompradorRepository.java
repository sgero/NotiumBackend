package com.example.notiumb.repository;

import com.example.notiumb.model.DatosComprador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDatosCompradorRepository extends JpaRepository<DatosComprador, Integer> {
}
