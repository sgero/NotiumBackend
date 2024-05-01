package com.example.notiumb.converter;
import com.example.notiumb.dto.EventoDTO;
import com.example.notiumb.dto.ListaOcioDTO;
import com.example.notiumb.dto.RppDTO;
import com.example.notiumb.model.Evento;
import com.example.notiumb.model.ListaOcio;
import com.example.notiumb.model.Rpp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IListaOcioMapper {

    IRppMapper rppMapper = Mappers.getMapper(IRppMapper.class);
    IEventoMapper eventoMapper = Mappers.getMapper(IEventoMapper.class);
    IListaOcioClienteMapper listaMapper = Mappers.getMapper(IListaOcioClienteMapper.class);

    @Mapping(source = "rpp", target = "rppDTO", qualifiedByName = "convertToRppDTO")
    @Mapping(source = "evento", target = "eventoDTO", qualifiedByName = "convertToEventoDTO")
    ListaOcioDTO toDTO(ListaOcio entity) ;
    @Mapping(source = "rppDTO", target = "rpp", qualifiedByName = "convertToRpp")
    @Mapping(source = "eventoDTO", target = "evento", qualifiedByName = "convertToEvento")
    ListaOcio toEntity(ListaOcioDTO dto);

    List<ListaOcioDTO> toDTO(List<ListaOcio> listEntity);

    List<ListaOcio> toEntity(List<ListaOcioDTO> listDTOs);

    @Named(value = "convertToRpp")
    default Rpp convertRpp(RppDTO dto){ return rppMapper.toEntity(dto); }

    @Named(value = "convertToRppDTO")
    default RppDTO convertRpp(Rpp entity){ return rppMapper.toDTO(entity); }

    @Named(value = "convertToEvento")
    default Evento convertEvento(EventoDTO dto){ return eventoMapper.toEntity(dto); }

    @Named(value = "convertToEventoDTO")
    default EventoDTO convertEvento(Evento entity){ return eventoMapper.toDTO(entity); }

}
