package com.example.notiumb.converter;

import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.dto.RppDTO;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.model.Rpp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IRppMapper {
    IOcioNocturnoMapper ocioNocturnoMapper = Mappers.getMapper(IOcioNocturnoMapper.class);

    @Mapping(source = "direccion", target = "direccionDTO")
    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "ocioNocturno", target = "ocioNocturnoDTO" , qualifiedByName = "transformarOcioNocturno")
    RppDTO toDTO(Rpp entity) ;
    @Mapping(source = "direccionDTO", target = "direccion")
    @Mapping(source = "userDTO", target = "user")
    @Mapping(source = "ocioNocturnoDTO", target = "ocioNocturno" , qualifiedByName = "transformarOcioNocturnoDTO")
    Rpp toEntity(RppDTO dto);

    List<RppDTO> toDTO(List<Rpp> listEntity);

    List<Rpp> toEntity(List<RppDTO> listDTOs);
    @Named("transformarOcioNocturnoDTO")
    default OcioNocturno transformarOcio(OcioNocturnoDTO dto){
        return ocioNocturnoMapper.toEntity(dto);
    }

    @Named("transformarOcioNocturno")
    default OcioNocturnoDTO transformarOcio(OcioNocturno entity){
        return ocioNocturnoMapper.toDTO(entity);
    }
}
