package com.example.notiumb.converter;


import com.example.notiumb.dto.ReservaDTO;

import com.example.notiumb.model.Reserva;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IReservaMapper {
    ReservaDTO toDTO(Reserva entity) ;

    Reserva toEntity(ReservaDTO dto);

    List<ReservaDTO> toDTO(List<Reserva> listEntity);

    List<Reserva> toEntity(List<ReservaDTO> listDTOs);
}
