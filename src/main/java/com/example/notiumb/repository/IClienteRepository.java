package com.example.notiumb.repository;

import com.example.notiumb.model.Cliente;
import com.example.notiumb.model.Rpp;
import com.example.notiumb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByIdAndActivoIsTrue(Integer id);

    @Query(nativeQuery = true, value = "select c.* from notium.cliente c " +
            "join notium.usuario u on c.id_usuario = u.id " +
            "where u.id = :id and u.activo = true and c.activo = true; ")
    Cliente findByIdUser(Integer id);

}
