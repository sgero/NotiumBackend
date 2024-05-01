package com.example.notiumb.repository;

import com.example.notiumb.model.EntradaOcio;
import com.example.notiumb.model.ListaOcio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEntradaOcioRepository extends JpaRepository<EntradaOcio, Integer> {

    EntradaOcio findEntradaOcioByEventoIdAndActivoIsTrue (Integer id);

}
