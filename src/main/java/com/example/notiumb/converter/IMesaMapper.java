package com.example.notiumb.converter;


import com.example.notiumb.dto.MesaDTO;
import com.example.notiumb.model.Mesa;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMesaMapper {
    Mesa toEntity(MesaDTO dto);

    MesaDTO toDTO(Mesa entity);

    List<MesaDTO> toDTO(List<Mesa> listEntity);

    List<Mesa> toEntity(List<MesaDTO> listDTOs);
}
