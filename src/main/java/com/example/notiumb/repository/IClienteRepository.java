package com.example.notiumb.repository;

import com.example.notiumb.model.Cliente;
import com.example.notiumb.model.Rpp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByIdAndActivoIsTrue(Integer id);

}
