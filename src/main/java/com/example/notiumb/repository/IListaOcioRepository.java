package com.example.notiumb.repository;

import com.example.notiumb.model.ListaOcio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IListaOcioRepository extends JpaRepository<ListaOcio, Integer> {
}
