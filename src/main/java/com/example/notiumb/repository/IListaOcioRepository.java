package com.example.notiumb.repository;

import com.example.notiumb.model.ListaOcio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IListaOcioRepository extends JpaRepository<ListaOcio, Integer> {

    List<ListaOcio> findAllByActivoIsTrue();

//    @Query(nativeQuery = true, value = "select * from notium.lista_ocio where id = :id and activo = true")
    Optional<ListaOcio> findByIdAndActivoIsTrue(Integer id);
    ListaOcio findListaOcioByEventoIdAndActivoIsTrue (Integer id);
    List<ListaOcio> findAllByEventoId (Integer id);

    List<ListaOcio> findAllByRppIdAndActivoIsTrue (Integer id);

    @Query(nativeQuery = true, value = "select l.* from notium.lista_ocio l join notium.evento e on l.id_evento = e.id " +
            "where l.id_rpp = :id and e.fecha > now() and l.activo = true and e.activo = true;")
    List<ListaOcio> listasActivas(Integer id);

}
