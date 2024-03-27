package com.example.notiumb.repository;

import com.example.notiumb.model.EntradaOcio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEntradaOcio extends JpaRepository<EntradaOcio, Integer> {
}
