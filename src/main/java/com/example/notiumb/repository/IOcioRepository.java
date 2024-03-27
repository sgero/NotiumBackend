package com.example.notiumb.repository;

import com.example.notiumb.model.Ocio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOcioRepository extends JpaRepository<Ocio, Integer> {
}