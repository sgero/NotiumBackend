package com.example.notiumb.converter;

import com.example.notiumb.dto.ProductoTipoBebidaDTO;
import com.example.notiumb.dto.ProductoTipoPlatoDTO;
import com.example.notiumb.model.ProductoTipoBebida;
import com.example.notiumb.model.ProductoTipoPlato;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoTipoBebidaMapper {

    ProductoTipoBebida toEntity(ProductoTipoBebidaDTO dto);

    ProductoTipoBebidaDTO toDTO(ProductoTipoBebida entity);

    List<ProductoTipoBebidaDTO> toDTO(List<ProductoTipoBebida> listEntity);

    List<ProductoTipoBebida> toEntity(List<ProductoTipoBebidaDTO> listDTOs);
}
