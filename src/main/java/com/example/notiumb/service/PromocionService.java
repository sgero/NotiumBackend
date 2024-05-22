package com.example.notiumb.service;

import com.example.notiumb.model.Promocion;
import com.example.notiumb.repository.IPromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromocionService {
    @Autowired
    private IPromocionRepository promocionRepository;
    public boolean verificarCodigo(Integer idPromocion, String codigo) {
        Promocion promocion = promocionRepository.findByIdAndActivoIsTrue(idPromocion);
        return promocion != null && promocion.getCodigo() != null && codigo.equals(promocion.getCodigo());
    }
}
