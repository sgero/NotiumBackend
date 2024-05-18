package com.example.notiumb.converter;

import com.example.notiumb.dto.PromocionDTO;
import com.example.notiumb.model.Promocion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPromocionMapper {
    @Mapping(source = "tipoPromocion", target = "tipo")
    Promocion toEntity(PromocionDTO dto);
    @Mapping(source = "tipo", target = "tipoPromocion")
    PromocionDTO toDTO(Promocion entity);

    List<Promocion> toEntity(List<PromocionDTO> dtos);

    List<PromocionDTO> toDTO(List<Promocion> entities);
}
