package com.example.notiumb.repository;

import com.example.notiumb.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMesaRepository extends JpaRepository<Mesa,Integer> {
}
