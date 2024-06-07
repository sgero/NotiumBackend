package com.example.notiumb.repository;

import com.example.notiumb.model.ListaOcioCliente;
import com.example.notiumb.model.ReservadoOcioCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface IListaOcioClienteRepository extends JpaRepository<ListaOcioCliente, Integer> {
    @Query(value = "select count(loc.id) from notium.lista_ocio_cliente loc " +
            "join notium.lista_ocio lo on loc.id_lista_ocio = lo.id " +
            "join notium.evento e on lo.id_evento = e.id " +
            "where e.id = :id and lo.activo = true;", nativeQuery = true)
    Integer cantidadClientesTotales (Integer id);

    List<ListaOcioCliente> findByClienteId(Integer idCliente);

    @Query(value = "select loc.codigo from notium.lista_ocio_cliente loc join notium.lista_ocio lo on loc.id_lista_ocio = lo.id join notium.evento e on e.id = lo.id_evento where e.id_ocio_nocturno = :id_ocio", nativeQuery = true)
    List<String> listadoCodigosReservaListado(Integer id_ocio);

    @Query(value = "select e.fecha from notium.lista_ocio_cliente loc join notium.lista_ocio lo on loc.id_lista_ocio = lo.id join notium.evento e on e.id = lo.id_evento where e.id_ocio_nocturno = :id_ocio and roc.codigo = %:codigo%", nativeQuery = true)
    Timestamp fechaEventoLista(Integer id_ocio, String codigo);

    @Query(value = "select e.id_ocio_nocturno from notium.lista_ocio_cliente loc join notium.lista_ocio lo on loc.id_lista_ocio = lo.id join notium.evento e on e.id = lo.id_evento where roc.codigo = %:codigo%", nativeQuery = true)
    Integer idOcioLista(String codigo);

    List<ListaOcioCliente> findAllByCliente_IdAndFechaIsBefore(Integer idCliente, Timestamp fechaEvento);
    List<ListaOcioCliente> findAllByCliente_IdAndFechaIsAfter(Integer idCliente, Timestamp fechaEvento);

}

