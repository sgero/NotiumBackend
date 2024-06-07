package com.example.notiumb.repository;

import com.example.notiumb.model.ChatMensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IChatMensajeRepository extends JpaRepository<ChatMensaje, Long> {
    List<ChatMensaje> findAllByChat_IdOrderByFechaDesc (Integer id);
}
