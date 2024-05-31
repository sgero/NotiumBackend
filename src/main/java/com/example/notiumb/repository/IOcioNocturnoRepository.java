package com.example.notiumb.repository;

import com.example.notiumb.model.CartaOcio;
import com.example.notiumb.model.OcioNocturno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOcioNocturnoRepository extends JpaRepository<OcioNocturno, Integer> {

    List<OcioNocturno> findAllByActivoIsTrue();
    Optional<OcioNocturno> findByIdAndActivoIsTrue(Integer id);

    OcioNocturno findByCif(String cif);

    OcioNocturno findTopByIdAndActivoIsTrue(Integer id);

}
