package com.example.notiumb.repository;

import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Integer> {

    @Query(value = "select t.hora_inicio from notium.turno_restaurante t where t.id_restaurante = :id_restaurante", nativeQuery = true)
    List<String> horaInicioTurnosPorRestaurante(Integer id_restaurante);

    @Query(value = "select t.hora_fin from notium.turno_restaurante t where t.id_restaurante = :id_restaurante", nativeQuery = true)
    List<String> horaFinTurnosPorRestaurante(Integer id_restaurante);

    List<Turno> findAllByRestauranteIdAndActivoIsTrue(Integer id);

    List<Turno> findByRestauranteEquals(Restaurante restaurante);

    @Query(value = "select tr.* from notium.turno_restaurante tr where tr.id_restaurante = :id_restaurante and tr.hora_inicio = %:hora_incio% and tr.hora_fin = %:hora_fin%", nativeQuery = true)
    Turno turnoCreado(Integer id_restaurante, Time hora_incio, Time hora_fin);

    Turno findTopById(Integer id);

    Turno[] findByRestauranteAndActivo(Restaurante restaurante, boolean b);

    @Query(value="select tr.* from notium.reserva_restaurante rr join notium.turno_restaurante tr on tr.id = rr.id_turno_restaurante where rr.id_restaurante = :id_restaurante  and rr.fecha = %:fecha%", nativeQuery = true)
    List<Turno> turnosReservasFecha(Integer id_restaurante, LocalDate fecha);

    @Query(value="select tr.* from notium.turno_restaurante tr join notium.turno_dias_semana td on tr.id = td.id_turno_restaurante where tr.id_restaurante = :id_restaurante and td.dia_semana = :numDiaSemana", nativeQuery = true)
    List<Turno> turnosFecha(Integer id_restaurante, Integer numDiaSemana);

}
