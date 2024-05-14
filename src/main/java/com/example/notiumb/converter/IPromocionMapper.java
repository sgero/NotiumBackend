package com.example.notiumb.converter;

import com.example.notiumb.dto.PromocionDTO;
import com.example.notiumb.model.Promocion;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPromocionMapper {
    Promocion toEntity(PromocionDTO dto);
    PromocionDTO toDTO(Promocion entity);

    List<Promocion> toEntity(List<PromocionDTO> dtos);

    List<PromocionDTO> toDTO(List<Promocion> entities);
}
