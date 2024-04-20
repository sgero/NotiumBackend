package com.example.notiumb.converter;
import com.example.notiumb.dto.ListaOcioDTO;
import com.example.notiumb.model.ListaOcio;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IListaOcioMapper {
    ListaOcioDTO toDTO(ListaOcio entity) ;

    ListaOcio toEntity(ListaOcioDTO dto);

    List<ListaOcioDTO> toDTO(List<ListaOcio> listEntity);

    List<ListaOcio> toEntity(List<ListaOcioDTO> listDTOs);
}
