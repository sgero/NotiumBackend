package com.example.notiumb.repository;

import com.example.notiumb.model.CartaOcio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartaOcioRepository extends JpaRepository<CartaOcio, Integer> {
}
