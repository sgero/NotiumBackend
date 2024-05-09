package com.example.notiumb.repository;

import com.example.notiumb.model.ListaOcioCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IListaOcioClienteRepository extends JpaRepository<ListaOcioCliente, Integer> {
    @Query(value = "select count(loc.id) from notium.lista_ocio_cliente loc " +
            "join notium.lista_ocio lo on loc.id_lista_ocio = lo.id " +
            "join notium.evento e on lo.id_evento = e.id " +
            "where e.id = :id and lo.activo = true;", nativeQuery = true)
    Integer cantidadClientesTotales (Integer id);
}
