package com.example.notiumb.converter;

import com.example.notiumb.dto.ChatMensajeDTO;
import com.example.notiumb.model.ChatMensaje;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IChatMensajeConverter {
    @Mapping(source = "chatEvento", target = "chatEventoDTO")
    @Mapping(source = "ocioNocturno", target = "ocioNocturnoDTO")
    ChatMensajeDTO toDTO(ChatMensaje entity) ;

    @Mapping(source = "chatEventoDTO", target = "chatEvento")
    @Mapping(source = "ocioNocturnoDTO", target = "ocioNocturno")
    ChatMensaje toEntity(ChatMensajeDTO dto);

    List<ChatMensajeDTO> toDTO(List<ChatMensaje> listEntity);

    List<ChatMensaje> toEntity(List<ChatMensajeDTO> listDTOs);
}
