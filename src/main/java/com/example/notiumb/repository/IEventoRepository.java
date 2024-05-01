package com.example.notiumb.repository;

import com.example.notiumb.model.Evento;
import com.example.notiumb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findAll();

    List<Evento> findByActivoIsTrue();

}
