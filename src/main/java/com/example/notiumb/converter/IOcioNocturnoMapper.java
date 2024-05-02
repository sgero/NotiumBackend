package com.example.notiumb.converter;

import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.model.OcioNocturno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOcioNocturnoMapper {

    @Mapping(source = "direccion", target = "direccionDTO")
    @Mapping(source = "user", target = "userDTO")
    OcioNocturnoDTO toDTO(OcioNocturno entity);
    @Mapping(source = "direccionDTO", target = "direccion")
    @Mapping(source = "userDTO", target = "user")
    OcioNocturno toEntity(OcioNocturnoDTO dto);
    List<OcioNocturnoDTO> toDTO(List<OcioNocturno> entities);
    List<OcioNocturno> toEntity(List<OcioNocturnoDTO> dtos);

}
