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
import java.util.Set;

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

    OcioNocturno findTopByIdAndActivoIsTrue(Integer id);

    @Query(nativeQuery = true, value = "select o.* from notium.ocio_nocturno o " +
            "join notium.evento e on o.id = e.id_ocio_nocturno " +
            "where e.id = :id and e.activo = true and o.activo = true;")
    OcioNocturno findByIdEvento(Integer id);

    @Query(nativeQuery = true, value = "select o.* from notium.chat_cliente cc " +
            "join notium.cliente c on cc.id_cliente = c.id " +
            "join notium.ocio_nocturno o on cc.id_chat = o.id " +
            "where c.id = :id ")
    Set<OcioNocturno> getChatsCliente(Integer id);

}
