package com.example.notiumb.converter;

import com.example.notiumb.dto.EntradaOcioDTO;
import com.example.notiumb.dto.EventoDTO;
import com.example.notiumb.dto.PromocionDTO;
import com.example.notiumb.model.EntradaOcio;
import com.example.notiumb.model.Evento;
import com.example.notiumb.model.Promocion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(componentModel = "spring")
public interface IEntradaOcioMapper {
    IEventoMapper eventoMapper = Mappers.getMapper(IEventoMapper.class);

    @Mapping(source = "eventoDTO", target = "evento", qualifiedByName = "conversorEventoDTO")
    EntradaOcio toEntity(EntradaOcioDTO dto);
    @Mapping(source = "evento", target = "eventoDTO", qualifiedByName = "conversorEventoEntity")
    EntradaOcioDTO toDTO(EntradaOcio entity);

    List<EntradaOcio> toEntity(List<EntradaOcioDTO> dtos);

    List<EntradaOcioDTO> toDTO(List<EntradaOcio> entities);
    @Named("conversorEventoDTO")
    default Evento transformarPromocion(EventoDTO dto){
        return eventoMapper.toEntity(dto);
    }

    @Named("conversorEventoEntity")
    default EventoDTO transformarPromocion(Evento entity){
        return eventoMapper.toDTO(entity);
    }
}
