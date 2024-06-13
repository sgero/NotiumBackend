package com.example.notiumb.repository;

import com.example.notiumb.model.CartaRestaurante;
import com.example.notiumb.model.TurnosDiasSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurnoSemanaRepository extends JpaRepository<TurnosDiasSemana, Integer> {
}
