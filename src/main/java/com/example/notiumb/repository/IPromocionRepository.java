package com.example.notiumb.repository;

import com.example.notiumb.model.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPromocionRepository extends JpaRepository<Promocion, Integer> {
    Promocion findByIdAndActivoIsTrue (Integer idPromocion);
}
