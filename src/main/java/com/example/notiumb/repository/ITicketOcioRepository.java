package com.example.notiumb.repository;

import com.example.notiumb.model.TicketOcio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITicketOcioRepository extends JpaRepository<TicketOcio, Integer> {
}
