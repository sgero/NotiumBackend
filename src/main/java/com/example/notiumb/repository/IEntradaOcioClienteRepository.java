package com.example.notiumb.repository;

import com.example.notiumb.model.EntradaOcioCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface IEntradaOcioClienteRepository extends JpaRepository<EntradaOcioCliente, Integer> {
    @Query(value = "select count(e.id) from notium.entrada_ocio_cliente e " +
            "join notium.entrada_ocio eo on eo.id = e.id_entrada_ocio " +
            "where eo.id = :id and eo.activo = true;", nativeQuery = true)
    Integer cantidadEntradasVendidas(Integer id);

    @Query(value = "select eoc.codigo from notium.entrada_ocio_cliente eoc join notium.entrada_ocio eo on eoc.id_entrada_ocio = eo.id join notium.evento e on e.id = eo.id_evento where e.id_ocio_nocturno = :id_ocio", nativeQuery = true)
    List<String> listadoCodigosReservaEntrada(Integer id_ocio);

    @Query(value = "select e.fecha from notium.reservado_ocio_cliente roc join notium.reservado_ocio ro on roc.id_reservado_ocio = ro.id join notium.evento e on e.id = ro.id_evento where e.id_ocio_nocturno = :id_ocio and roc.codigo = %:codigo%", nativeQuery = true)
    Timestamp fechaEventoEntrada(Integer id_ocio, String codigo);

    @Query(value = "select e.id_ocio_nocturno from notium.reservado_ocio_cliente roc join notium.reservado_ocio ro on roc.id_reservado_ocio = ro.id join notium.evento e on e.id = ro.id_evento where roc.codigo = %:codigo%", nativeQuery = true)
    Integer idOcioEntrada(String codigo);

    @Query(value = "select eoc.* from notium.entrada_ocio_cliente eoc " +
            "join notium.entrada_ocio eo on eoc.id_entrada_ocio = eo.id " +
            "join notium.cliente c on eoc.id_cliente = c.id " +
            "join notium.evento e on eo.id_evento = e.id " +
            "where c.id = :id and c.activo = true and e.fecha < :fecha order by e.fecha desc;", nativeQuery = true)
    List<EntradaOcioCliente> getPasadas (Integer id, Timestamp fecha);

    @Query(value = "select eoc.* from notium.entrada_ocio_cliente eoc " +
            "join notium.entrada_ocio eo on eoc.id_entrada_ocio = eo.id " +
            "join notium.cliente c on eoc.id_cliente = c.id " +
            "join notium.evento e on eo.id_evento = e.id " +
            "where c.id = :id and c.activo = true and e.fecha > :fecha order by e.fecha desc;", nativeQuery = true)
    List<EntradaOcioCliente> getFuturas (Integer id, Timestamp fecha);

}
