package com.example.notiumb.converter;

import com.example.notiumb.dto.DatosCompradorDTO;
import com.example.notiumb.model.DatosComprador;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface IDatosCompradorMapper {
    @Mapping(source = "reservadoOcioClienteDTO.clienteDTO", target = "reservadoOcioCliente.cliente")
    @Mapping(source = "reservadoOcioClienteDTO.reservadoOcioDTO", target = "reservadoOcioCliente.reservadoOcio")
    @Mapping(source = "reservadoOcioClienteDTO.promocionDTO", target = "reservadoOcioCliente.promocion")
    @Mapping(source = "reservadoOcioClienteDTO", target = "reservadoOcioCliente")
    DatosComprador toEntity(DatosCompradorDTO dto);
    @Mapping(source = "reservadoOcioCliente.cliente", target = "reservadoOcioClienteDTO.clienteDTO")
    @Mapping(source = "reservadoOcioCliente.reservadoOcio", target = "reservadoOcioClienteDTO.reservadoOcioDTO")
    @Mapping(source = "reservadoOcioCliente.promocion", target = "reservadoOcioClienteDTO.promocionDTO")
    @Mapping(source = "reservadoOcioCliente", target = "reservadoOcioClienteDTO")
    DatosCompradorDTO toDTO(DatosComprador entity);

    List<DatosComprador> toEntity(List<DatosCompradorDTO> dtos);

    List<DatosCompradorDTO> toDTO(List<DatosComprador> entities);
    List<DatosCompradorDTO> toDTO(Set<DatosComprador> entities);
}
