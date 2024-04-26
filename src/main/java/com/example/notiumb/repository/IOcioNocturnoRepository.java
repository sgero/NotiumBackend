package com.example.notiumb.repository;

import com.example.notiumb.model.OcioNocturno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOcioNocturnoRepository extends JpaRepository<OcioNocturno, Integer> {
}
