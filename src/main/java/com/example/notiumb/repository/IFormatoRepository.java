package com.example.notiumb.repository;


import com.example.notiumb.model.Formato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFormatoRepository extends JpaRepository<Formato, Integer> {

    Formato findTopById(Integer id);
}
