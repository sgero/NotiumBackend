package com.example.notiumb.repository;

import com.example.notiumb.dto.ListaOcioDTO;
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

}
