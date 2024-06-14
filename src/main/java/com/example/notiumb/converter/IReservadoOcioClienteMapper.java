package com.example.notiumb.converter;
import com.example.notiumb.dto.ReservadoOcioClienteDTO;
import com.example.notiumb.model.ReservadoOcioCliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IReservadoOcioClienteMapper {

    @Mapping(source = "cliente", target = "clienteDTO")
    @Mapping(source = "reservadoOcio", target = "reservadoOcioDTO")
    @Mapping(source = "promocion", target = "promocionDTO")
    @Mapping(source = "reservadoOcio.evento", target = "reservadoOcioDTO.eventoDTO")
    @Mapping(source = "reservadoOcio.evento.ocioNocturno", target = "reservadoOcioDTO.eventoDTO.ocioNocturnoDTO")
    ReservadoOcioClienteDTO toDTO(ReservadoOcioCliente entity) ;

    @Mapping(source = "clienteDTO", target = "cliente")
    @Mapping(source = "reservadoOcioDTO", target = "reservadoOcio")
    @Mapping(source = "promocionDTO", target = "promocion")
    @Mapping(source = "reservadoOcioDTO.eventoDTO", target = "reservadoOcio.evento")
    @Mapping(source = "reservadoOcioDTO.eventoDTO.ocioNocturnoDTO", target = "reservadoOcio.evento.ocioNocturno")
    ReservadoOcioCliente toEntity(ReservadoOcioClienteDTO dto);

    List<ReservadoOcioClienteDTO> toDTO(List<ReservadoOcioCliente> listEntity);

    List<ReservadoOcioCliente> toEntity(List<ReservadoOcioClienteDTO> listDTOs);

}
