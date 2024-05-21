package com.example.notiumb.repository;

import com.example.notiumb.model.Rpp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRppRepository extends JpaRepository <Rpp, Integer> {

    List<Rpp> findAllByActivoIsTrue();

    Optional<Rpp> findByIdAndActivoIsTrue(Integer id);

    List<Rpp> findAllByOcioNocturnoIdAndActivoIsTrue (Integer idOcio);

}
