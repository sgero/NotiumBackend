package com.example.notiumb.converter;


import com.example.notiumb.dto.ProductoDTO;
import com.example.notiumb.model.Producto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductoMapper {
    Producto toEntity(ProductoDTO dto);

    ProductoDTO toDTO(Producto entity);

    List<ProductoDTO> toDTO(List<Producto> listEntity);

    List<Producto> toEntity(List<ProductoDTO> listDTOs);
}
