package com.example.notiumb.repository;

import com.example.notiumb.model.Reserva;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IReservaRepository extends JpaRepository<Reserva, Integer> {

    @Query(value = "select rr.codigo_reserva from notium.reserva_restaurante rr where rr.id_restaurante = :id_restaurante", nativeQuery = true)
    List<String> listadoCodigosReserva(Integer id_restaurante);

    @Query(value = "select CAST(rr.fecha AS DATE) from notium.reserva_restaurante rr where rr.id_restaurante = :id_restaurante", nativeQuery = true)
    List<Date> listadoReservaFecha(Integer id_restaurante);

    List<Reserva> findByFechaEqualsAndRestauranteEqualsAndTurnoEquals(LocalDate fecha , Restaurante restaurante , Turno turno);

    Reserva findByIdAndActivoIsTrue(Integer id);

    List<Reserva> findAllByActivoIsTrue();

    boolean existsByCodigoReserva(String codigoReserva);

    @Query("SELECT r FROM Reserva r WHERE r.turno = :turno AND r.fecha = :fecha")
    java.util.List<Reserva> findByTurnoAndFecha(Turno turno, LocalDate fecha);

}
