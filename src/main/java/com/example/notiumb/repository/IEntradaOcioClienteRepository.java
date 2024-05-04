package com.example.notiumb.repository;

import com.example.notiumb.model.EntradaOcioCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IEntradaOcioClienteRepository extends JpaRepository<EntradaOcioCliente, Integer> {
    @Query(value = "select count(e.id) from notium.entrada_ocio_cliente e " +
            "join notium.entrada_ocio eo on eo.id = e.id_entrada_ocio " +
            "where eo.id = :id and eo.activo = true;", nativeQuery = true)
    Integer cantidadEntradasVendidas(Integer id);


}
