package com.example.notiumb.converter;

import com.example.notiumb.dto.DatosCompradorDTO;
import com.example.notiumb.model.DatosComprador;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IDatosCompradorMapper {
    @Mapping(source = "reservadoOcioClienteDTO", target = "reservadoOcioCliente")
    DatosComprador toEntity(DatosCompradorDTO dto);
    @Mapping(source = "reservadoOcioCliente", target = "reservadoOcioClienteDTO")
    DatosCompradorDTO toDTO(DatosComprador entity);

    List<DatosComprador> toEntity(List<DatosCompradorDTO> dtos);

    List<DatosCompradorDTO> toDTO(List<DatosComprador> entities);
}
