package com.example.notiumb.repository;

import com.example.notiumb.model.ReservadoOcioCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
