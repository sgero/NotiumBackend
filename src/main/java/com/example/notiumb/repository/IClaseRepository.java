package com.example.notiumb.repository;

import com.example.notiumb.model.Clase;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClaseRepository extends JpaRepository<Clase, Integer> {

    List<Clase> findAll();

    Clase findTopById(Integer id);

}
