package com.example.notiumb.converter;

import com.example.notiumb.dto.FormatoDTO;

import com.example.notiumb.model.Formato;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IFormatoMapper {

    Formato toEntity(FormatoDTO dto);

    FormatoDTO toDTO(Formato entity);

    List<FormatoDTO> toDTO(List<Formato> listEntity);

    List<Formato> toEntity(List<FormatoDTO> listDTOs);
}
