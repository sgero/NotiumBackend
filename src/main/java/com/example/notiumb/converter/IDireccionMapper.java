package com.example.notiumb.converter;

import com.example.notiumb.dto.DireccionDTO;
import com.example.notiumb.model.Direccion;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IDireccionMapper {
    DireccionDTO toDTO(Direccion entity) ;

    Direccion toEntity(DireccionDTO dto);

    List<DireccionDTO> toDTO(List<Direccion> listEntity);

    List<Direccion> toEntity(List<DireccionDTO> listDTOs);
}
