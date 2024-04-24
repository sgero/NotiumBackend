package com.example.notiumb.converter;


import com.example.notiumb.dto.CartaOcioDTO;
import com.example.notiumb.dto.CartaRestauranteDTO;
import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.model.CartaOcio;
import com.example.notiumb.model.CartaRestaurante;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.model.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")

public interface ICartaRestauranteMapper {


    IRestauranteMapper restauranteMapper = Mappers.getMapper(IRestauranteMapper.class);

    @Mapping(source = "restaurante", target = "restauranteDTO" , qualifiedByName = "converRestaurante")
    CartaRestauranteDTO toDTO(CartaRestaurante entity) ;
    @Mapping(source = "restauranteDTO", target = "restaurante", qualifiedByName = "convertRestauranteDTO")
    CartaRestaurante toEntity(CartaRestauranteDTO dto);


    List<CartaRestauranteDTO> toDTO(List<CartaRestaurante> listEntities);

    List<CartaRestaurante> toEntity(List<CartaRestauranteDTO> listDTOs);

    @Named("convertRestauranteDTO")
    default Restaurante convertRest(RestauranteDTO dto)  {return restauranteMapper.toEntity(dto);
    }

    @Named("converRestaurante")
    default RestauranteDTO convertRest(Restaurante entity){
        return restauranteMapper.toDTO(entity);
    }


}
