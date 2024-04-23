package com.example.notiumb.converter;

import com.example.notiumb.dto.CartaOcioDTO;
import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.model.CartaOcio;
import com.example.notiumb.model.OcioNocturno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICartaOcioMapper {

    IOcioNocturnoMapper ocioNocturnoMapper = Mappers.getMapper(IOcioNocturnoMapper.class);

    @Mapping(source = "ocioNocturno", target = "ocioNocturnoDTO" , qualifiedByName = "convertOcioNocturno")
    CartaOcioDTO toDTO(CartaOcio entity) ;
    @Mapping(source = "ocioNocturnoDTO", target = "ocioNocturno", qualifiedByName = "convertOcioNocturnoDTO")
    CartaOcio toEntity(CartaOcioDTO dto);

    List<CartaOcioDTO> toDTO(List<CartaOcio> listEntities);

    List<CartaOcio> toEntity(List<CartaOcioDTO> listDTOs);

    @Named("convertOcioNocturnoDTO")
    default OcioNocturno convertOcio(OcioNocturnoDTO dto){
        return ocioNocturnoMapper.toEntity(dto);
    }

    @Named("convertOcioNocturno")
    default OcioNocturnoDTO convertOcio(OcioNocturno entity){
        return ocioNocturnoMapper.toDTO(entity);
    }


}
