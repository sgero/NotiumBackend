package com.example.notiumb.repository;

import com.example.notiumb.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMesaRepository extends JpaRepository<Mesa,Integer> {

    @Query(value = "select sum(m.num_plazas) from notium.mesa_restaurante m where m.id_restaurante = :id_restaurante and m.activo!= false", nativeQuery = true)
    Integer numeroDePlazaPorMesasRestaurante(Integer id_restaurante);
}
