package com.example.notiumb.repository;

import com.example.notiumb.model.Evento;
import com.example.notiumb.model.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IEventoRepository extends JpaRepository<Evento, Integer> {
    @NonNull
    List<Evento> findAll();

    List<Evento> findByActivoIsTrueAndFechaAfterOrderByFechaDesc(Date fechaActual);
    @Query(value = "select e.* from notium.evento e " +
            "join notium.ocio_nocturno o on e.id_ocio_nocturno = o.id " +
            "where o.id = :idOcio and e.fecha between :fechaInicio and :fechaFin and e.activo = true " +
            "order by e.fecha desc;", nativeQuery = true)
    List<Evento> getEventosBetweenDatesByOcioId(Integer idOcio, Date fechaInicio, Date fechaFin);

    @Query(value = "select e.* from notium.evento e " +
            "where e.fecha between :fechaInicio and :fechaFin and e.activo = true " +
            "order by e.fecha desc;", nativeQuery = true)
    List<Evento> getEventosBetweenDates(Date fechaInicio, Date fechaFin);

}
