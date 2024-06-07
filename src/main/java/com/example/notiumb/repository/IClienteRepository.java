package com.example.notiumb.repository;

import com.example.notiumb.model.Cliente;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.model.Rpp;
import com.example.notiumb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByIdAndActivoIsTrue(Integer id);

    @Query(nativeQuery = true, value = "select c.* from notium.cliente c " +
            "join notium.usuario u on c.id_usuario = u.id " +
            "where u.id = :id and u.activo = true and c.activo = true; ")
    Cliente findByIdUser(Integer id);

    @Query(nativeQuery = true, value = "select count(cc.id_cliente) from notium.chat_cliente cc " +
            "join notium.cliente c on cc.id_cliente = c.id " +
            "join notium.ocio_nocturno o on cc.id_chat = o.id " +
            "join notium.usuario u on c.id_usuario = u.id " +
            "where u.id = :idCliente and  o.id = :idChat and c.activo = true and o.activo = true;")
    Integer countClienteEnChat(Integer idCliente, Integer idChat);
}
