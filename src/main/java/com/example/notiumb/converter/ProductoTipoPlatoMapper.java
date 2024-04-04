package com.example.notiumb.converter;

import com.example.notiumb.dto.ProductoTipoPlatoDTO;
import com.example.notiumb.model.ProductoTipoPlato;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoTipoPlatoMapper {

    ProductoTipoPlato toEntity(ProductoTipoPlatoDTO dto);

    ProductoTipoPlatoDTO toDTO(ProductoTipoPlato entity);

    List<ProductoTipoPlatoDTO> toDTO(List<ProductoTipoPlato> listEntity);

    List<ProductoTipoPlato> toEntity(List<ProductoTipoPlatoDTO> listDTOs);
}
