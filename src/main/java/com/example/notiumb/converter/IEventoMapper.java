package com.example.notiumb.converter;

import com.example.notiumb.dto.EventoDTO;
import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.model.Evento;
import com.example.notiumb.model.OcioNocturno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IEventoMapper {
    IOcioNocturnoMapper ocioNocturnoMapper = Mappers.getMapper(IOcioNocturnoMapper.class);

    @Mapping(source = "ocioNocturnoDTO", target = "ocioNocturno" , qualifiedByName = "transformarOcioNocturnoDTO")
    Evento toEntity(EventoDTO dto);

    @Mapping(source = "ocioNocturno", target = "ocioNocturnoDTO" , qualifiedByName = "transformarOcioNocturno")
    EventoDTO toDTO(Evento entity);

    List<Evento> toEntity(List<EventoDTO> dtos);

    List<EventoDTO> toDTO(List<Evento> entities);

    @Named("transformarOcioNocturnoDTO")
    default OcioNocturno transformarOcio(OcioNocturnoDTO dto){
        return ocioNocturnoMapper.toEntity(dto);
    }

    @Named("transformarOcioNocturno")
    default OcioNocturnoDTO transformarOcio(OcioNocturno entity){
        return ocioNocturnoMapper.toDTO(entity);
    }
}
