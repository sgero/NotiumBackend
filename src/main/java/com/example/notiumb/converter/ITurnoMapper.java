package com.example.notiumb.converter;


import com.example.notiumb.dto.TurnoDTO;
import com.example.notiumb.model.Turno;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITurnoMapper {
    TurnoDTO toDTO(Turno entity) ;

    Turno toEntity(TurnoDTO dto);

    List<TurnoDTO> toDTO(List<Turno> listEntity);

    List<Turno> toEntity(List<TurnoDTO> listDTOs);
}
