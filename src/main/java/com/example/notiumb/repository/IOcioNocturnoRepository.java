package com.example.notiumb.repository;

import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.model.CartaOcio;
import com.example.notiumb.model.Cliente;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOcioNocturnoRepository extends JpaRepository<OcioNocturno, Integer> {

    List<OcioNocturno> findAllByActivoIsTrue();
    Optional<OcioNocturno> findByIdAndActivoIsTrue(Integer id);

    OcioNocturno findByUserEqualsAndActivoIsTrue(User user);
    OcioNocturno findByCif(String cif);

    @Query(nativeQuery = true, value = "select o.* from notium.ocio_nocturno o " +
            "join notium.usuario u on o.id_usuario = u.id " +
            "where u.id = :id and u.activo = true and o.activo = true;")
    OcioNocturno findByIdUser(Integer id);

}
