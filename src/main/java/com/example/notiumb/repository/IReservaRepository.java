package com.example.notiumb.repository;

import com.example.notiumb.dto.TurnoDTO;
import com.example.notiumb.model.Mesa;
import com.example.notiumb.model.Reserva;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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

    List<Reserva> findByFechaAndRestaurante(LocalDate fecha, Restaurante restaurante);

    List<Reserva> findByFecha(LocalDate fecha);
    @Query(value = "select rr.* from notium.reserva_restaurante rr where rr.codigo_reserva = %:codigoReserva%", nativeQuery = true)
    Reserva buscarValoracion(String codigoReserva);

    List<Reserva> findAllByRestauranteIdAndActivoIsTrue(Integer id);

    @Query(value = "select rr.* from notium.reserva_restaurante rr join notium.cliente on rr.id_cliente = cliente.id join notium.usuario u on cliente.id_usuario = u.id where u.id = :id_usuario", nativeQuery = true)
    List<Reserva> reservasPorUsuario(Integer id_usuario);

    @Query(value="select rr.* from notium.reserva_restaurante rr where rr.id_restaurante = :id_restaurante and rr.id_turno_restaurante = :id_turno and rr.fecha = %:fecha%", nativeQuery = true)
    List<Reserva> reservaFechaTurno(Integer id_restaurante, Integer id_turno, LocalDate fecha);


}
