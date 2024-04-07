package com.example.notiumb.converter;

import com.example.notiumb.dto.CartaOcioDTO;
import com.example.notiumb.dto.ListaOcioClienteDTO;
import com.example.notiumb.model.CartaOcio;
import com.example.notiumb.model.ListaOcioCliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IListaOcioClienteMapper {

    ListaOcioClienteDTO toDTO(ListaOcioCliente entity) ;

    ListaOcioCliente toEntity(ListaOcioClienteDTO dto);

    List<ListaOcioClienteDTO> toDTO(List<ListaOcioCliente> listEntity);

    List<ListaOcioCliente> toEntity(List<ListaOcioClienteDTO> listDTOs);

}
