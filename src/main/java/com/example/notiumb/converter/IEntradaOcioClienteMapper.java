package com.example.notiumb.converter;

import com.example.notiumb.dto.EntradaOcioClienteDTO;
import com.example.notiumb.model.EntradaOcioCliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IEntradaOcioClienteMapper {
    @Mapping(source = "entradaOcioDTO", target = "entradaOcio")
    @Mapping(source = "clienteDTO", target = "cliente")
    EntradaOcioCliente toEntity(EntradaOcioClienteDTO dto);
    @Mapping(source = "cliente", target = "clienteDTO")
    @Mapping(source = "entradaOcio", target = "entradaOcioDTO")
    EntradaOcioClienteDTO toDTO(EntradaOcioCliente entity);

    List<EntradaOcioCliente> toEntity(List<EntradaOcioClienteDTO> dtos);

    List<EntradaOcioClienteDTO> toDTO(List<EntradaOcioCliente> entities);
}
