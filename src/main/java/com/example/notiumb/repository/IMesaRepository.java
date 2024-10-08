package com.example.notiumb.repository;

import com.example.notiumb.model.Mesa;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IMesaRepository extends JpaRepository<Mesa,Integer> {

    @Query(value = "select sum(m.num_plazas) from notium.mesa_restaurante m where m.id_restaurante = :id_restaurante and m.activo!= false", nativeQuery = true)
    Integer numeroDePlazaPorMesasRestaurante(Integer id_restaurante);

    List<Mesa> findByRestauranteEquals(Restaurante restaurante);

    List<Mesa> findByRestaurante(Restaurante restaurante);

    List<Mesa> findAllByRestaurante_Id(Integer id);

}
