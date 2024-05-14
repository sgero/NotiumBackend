package com.example.notiumb.repository;

import com.example.notiumb.model.ReservadoOcio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IReservadoOcioRepository extends JpaRepository<ReservadoOcio, Integer> {

    List<ReservadoOcio> findAllByActivoIsTrue();

    Optional<ReservadoOcio> findByIdAndActivoIsTrue(Integer id);

    ReservadoOcio findReservadoOcioByEventoIdAndActivoIsTrue (Integer id);


}
