package com.example.notiumb.converter;

import com.example.notiumb.dto.EntradaOcioDTO;
import com.example.notiumb.model.EntradaOcio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface IEntradaOcioMapper {
    @Mapping(source = "eventoDTO", target = "evento")
    EntradaOcio toEntity(EntradaOcioDTO dto);
    @Mapping(source = "evento", target = "eventoDTO")
    EntradaOcioDTO toDTO(EntradaOcio entity);

    List<EntradaOcio> toEntity(List<EntradaOcioDTO> dtos);

    List<EntradaOcioDTO> toDTO(List<EntradaOcio> entities);
}
