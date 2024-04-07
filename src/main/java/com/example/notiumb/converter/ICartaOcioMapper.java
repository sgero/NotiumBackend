package com.example.notiumb.converter;
import com.example.notiumb.dto.CartaOcioDTO;
import com.example.notiumb.model.CartaOcio;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ICartaOcioMapper {

    CartaOcioDTO toDTO(CartaOcio entity) ;

    CartaOcio toEntity(CartaOcioDTO dto);

    List<CartaOcioDTO> toDTO(List<CartaOcio> listEntity);

    List<CartaOcio> toEntity(List<CartaOcioDTO> listDTOs);

}
