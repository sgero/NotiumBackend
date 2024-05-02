package com.example.notiumb.converter;


import com.example.notiumb.dto.FormatoDTO;
import com.example.notiumb.dto.ProductoDTO;
import com.example.notiumb.model.Formato;
import com.example.notiumb.model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductoMapper {

    Producto toEntity(ProductoDTO dto);

    ProductoDTO toDTO(Producto entity);

    List<ProductoDTO> toDTO(List<Producto> listEntity);

    List<Producto> toEntity(List<ProductoDTO> listDTOs);
}
