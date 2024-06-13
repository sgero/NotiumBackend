package com.example.notiumb.repository;

import com.example.notiumb.model.RestauranteClase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRestauranteClaseRepository extends JpaRepository<RestauranteClase, Integer> {


}
