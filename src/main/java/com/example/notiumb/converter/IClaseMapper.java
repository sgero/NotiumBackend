package com.example.notiumb.converter;


import com.example.notiumb.dto.ClaseDTO;
import com.example.notiumb.model.Clase;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IClaseMapper {
    Clase toEntity(ClaseDTO dto);

    ClaseDTO toDTO(Clase entity);

    List<ClaseDTO> toDTO(List<Clase> listEntity);

    List<Clase> toEntity(List<ClaseDTO> listDTOs);
}
