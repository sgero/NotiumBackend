package com.example.notiumb.converter;
import com.example.notiumb.dto.ReservadoOcioDTO;
import com.example.notiumb.model.ReservadoOcio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IReservadoOcioMapper {

    @Mapping(source = "eventoDTO", target = "evento")
    ReservadoOcio toEntity (ReservadoOcioDTO dto);
    @Mapping(source = "evento", target = "eventoDTO")
    ReservadoOcioDTO toDTO(ReservadoOcio entity);

    List<ReservadoOcioDTO> toDTO(List<ReservadoOcio> listEntity);

    List<ReservadoOcio> toEntity(List<ReservadoOcioDTO> listDTOs);

}
