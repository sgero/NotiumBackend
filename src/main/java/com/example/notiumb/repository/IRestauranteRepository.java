package com.example.notiumb.repository;

import com.example.notiumb.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRestauranteRepository extends JpaRepository<Restaurante , Integer> {

    List<Restaurante> findAll();
}
