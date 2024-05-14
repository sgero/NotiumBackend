package com.example.notiumb.converter;

import com.example.notiumb.dto.EntradaOcioClienteDTO;
import com.example.notiumb.dto.ListaOcioClienteDTO;
import com.example.notiumb.model.EntradaOcioCliente;
import com.example.notiumb.model.ListaOcioCliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IListaOcioClienteMapper {
    @Mapping(source = "entradaOcioDTO", target = "entradaOcio")
    @Mapping(source = "clienteDTO", target = "cliente")
    @Mapping(source = "promocionDTO", target = "promocion")
    @Mapping(source = "datosCompradorDTO", target = "datosComprador")
    ListaOcioClienteDTO toDTO(ListaOcioCliente entity) ;
    @Mapping(source = "cliente", target = "clienteDTO")
    @Mapping(source = "entradaOcio", target = "entradaOcioDTO")
    @Mapping(source = "promocion", target = "promocionDTO")
    @Mapping(source = "datosComprador", target = "datosCompradorDTO")
    ListaOcioCliente toEntity(ListaOcioClienteDTO dto);

    List<ListaOcioClienteDTO> toDTO(List<ListaOcioCliente> listEntity);

    List<EntradaOcioCliente> toEntity(List<EntradaOcioClienteDTO> entradasOcioClienteLista);
}
