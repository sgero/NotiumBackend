package com.example.notiumb.converter;


import com.example.notiumb.dto.ProductoFormatoDTO;

import com.example.notiumb.model.ProductoFormato;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductoFormatoMapper {

    ProductoFormato toEntity(ProductoFormatoDTO dto);

    ProductoFormatoDTO toDTO(ProductoFormato entity);

    List<ProductoFormatoDTO> toDTO(List<ProductoFormato> listEntity);

    List<ProductoFormato> toEntity(List<ProductoFormatoDTO> listDTOs);
}
