package com.example.notiumb.converter;
import com.example.notiumb.dto.ReservadoOcioDTO;
import com.example.notiumb.model.ReservadoOcio;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IReservadoOcioMapper {

    ReservadoOcio toEntity (ReservadoOcioDTO dto);

    ReservadoOcioDTO toDTO(ReservadoOcio entity);

    List<ReservadoOcioDTO> toDTO(List<ReservadoOcio> listEntity);

    List<ReservadoOcio> toEntity(List<ReservadoOcioDTO> listDTOs);

}
