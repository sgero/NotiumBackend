package com.example.notiumb.repository;

import com.example.notiumb.model.CartaOcio;
import com.example.notiumb.model.OcioNocturno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICartaOcioRepository extends JpaRepository<CartaOcio, Integer> {

    List<CartaOcio> findAllByActivoIsTrue();
    Optional<CartaOcio> findByIdAndActivoIsTrue(Integer id);
    CartaOcio findTopByOcioNocturnoEqualsAndActivoIsTrue(OcioNocturno ocioNocturno);
    CartaOcio findByOcioNocturnoId(Integer idOcio);
}
