package com.example.notiumb.repository;

import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.Rpp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRppRepository extends JpaRepository <Rpp, Integer> {

    List<Rpp> findAllByActivoIsTrue();

    Optional<Rpp> findByIdAndActivoIsTrue(Integer id);

    @Query(nativeQuery = true, value = "SELECT r.* FROM notium.rpp r\n" +
            "WHERE r.id_usuario = :id and activo = true;")
    Rpp findByIdUser(Integer id);

    List<Rpp> findAllByOcioNocturnoIdAndActivoIsTrue (Integer idOcio);

}
