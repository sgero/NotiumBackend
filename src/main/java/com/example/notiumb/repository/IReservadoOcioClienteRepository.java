package com.example.notiumb.repository;

import com.example.notiumb.model.ReservadoOcioCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface IReservadoOcioClienteRepository extends JpaRepository <ReservadoOcioCliente, Integer> {
    @Query(value = "select count(roc.id) from notium.reservado_ocio_cliente roc " +
            "join notium.reservado_ocio ro on roc.id_reservado_ocio = ro.id " +
            "where ro.id = :id and ro.activo = true;", nativeQuery = true)
    Integer cantidadReservadosVendidos (Integer id);

    @Query(value = "select sum(roc.cantidad_personas) from notium.reservado_ocio_cliente roc " +
            "join notium.reservado_ocio ro on roc.id_reservado_ocio = ro.id " +
            "where ro.id = :id and ro.activo = true;", nativeQuery = true)
    Integer cantidadPersonas (Integer id);

    @Query(value = "select roc.codigo from notium.reservado_ocio_cliente roc join notium.reservado_ocio ro on roc.id_reservado_ocio = ro.id join notium.evento e on e.id = ro.id_evento where e.id_ocio_nocturno = :id_ocio", nativeQuery = true)
    List<String> listadoCodigosReservaReservado(Integer id_ocio);

    @Query(value = "select e.fecha from notium.reservado_ocio_cliente roc join notium.reservado_ocio ro on roc.id_reservado_ocio = ro.id join notium.evento e on e.id = ro.id_evento where e.id_ocio_nocturno = :id_ocio and roc.codigo = %:codigo%", nativeQuery = true)
    Timestamp fechaEventoReservado(Integer id_ocio, String codigo);

    @Query(value = "select e.id_ocio_nocturno from notium.reservado_ocio_cliente roc join notium.reservado_ocio ro on roc.id_reservado_ocio = ro.id join notium.evento e on e.id = ro.id_evento where roc.codigo = %:codigo%", nativeQuery = true)
    Integer idOcioReservado(String codigo);


}
