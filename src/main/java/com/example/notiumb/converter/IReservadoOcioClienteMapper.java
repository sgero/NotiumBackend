package com.example.notiumb.converter;
import com.example.notiumb.dto.ReservadoOcioClienteDTO;
import com.example.notiumb.model.ReservadoOcioCliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IReservadoOcioClienteMapper {

    ReservadoOcioClienteDTO toDTO(ReservadoOcioCliente entity) ;

    ReservadoOcioCliente toEntity(ReservadoOcioClienteDTO dto);

    List<ReservadoOcioClienteDTO> toDTO(List<ReservadoOcioCliente> listEntity);

    List<ReservadoOcioCliente> toEntity(List<ReservadoOcioClienteDTO> listDTOs);

}
