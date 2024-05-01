package com.example.notiumb.repository;

import com.example.notiumb.model.CartaOcio;
import com.example.notiumb.model.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClaseRepository extends JpaRepository<Clase, Integer> {
}
