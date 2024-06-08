package com.example.notiumb.repository;

import com.example.notiumb.model.ListaOcioCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.sql.Timestamp;

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



    @Query(value = "select loc.* from notium.lista_ocio_cliente loc " +
            "join notium.lista_ocio lo on loc.id_lista_ocio = lo.id " +
            "join notium.cliente c on loc.id_cliente = c.id " +
            "join notium.evento e on lo.id_evento = e.id " +
            "where c.id = :id and c.activo = true and e.fecha < :fecha order by e.fecha desc;", nativeQuery = true)
    List<ListaOcioCliente> getPasadas(Integer id, Timestamp fecha);

    @Query(value = "select loc.* from notium.lista_ocio_cliente loc " +
            "join notium.lista_ocio lo on loc.id_lista_ocio = lo.id " +
            "join notium.cliente c on loc.id_cliente = c.id " +
            "join notium.evento e on lo.id_evento = e.id " +
            "where c.id = :id and c.activo = true and e.fecha > :fecha order by e.fecha desc;", nativeQuery = true)
    List<ListaOcioCliente> getFuturas(Integer id, Timestamp fecha);

}

