package com.example.notiumb.repository;

import com.example.notiumb.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IDireccionRepository extends JpaRepository<Direccion,Integer> {

    Direccion findTopByCalleOrderByIdDesc(String calle);

    Direccion findTopById(Integer id);

}
