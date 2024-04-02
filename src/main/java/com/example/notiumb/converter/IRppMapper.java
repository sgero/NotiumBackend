package com.example.notiumb.converter;

import com.example.notiumb.dto.RppDTO;
import com.example.notiumb.model.Rpp;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IRppMapper {
    RppDTO toDTO(Rpp entity) ;

    Rpp toEntity(RppDTO dto);

    List<RppDTO> toDTO(List<Rpp> listEntity);

    List<Rpp> toEntity(List<RppDTO> listDTOs);
}
