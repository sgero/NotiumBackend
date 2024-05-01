package com.example.notiumb.converter;


import com.example.notiumb.dto.ProductoDTO;
import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.dto.TurnoDTO;
import com.example.notiumb.model.Producto;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.Turno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITurnoMapper {

    IRestauranteMapper restauranteMapper = Mappers.getMapper(IRestauranteMapper.class);

    @Mapping(source = "restaurante", target = "restauranteDTO", qualifiedByName = "transformarRestauranteToDTO")
    TurnoDTO toDTO(Turno entity) ;

    @Mapping(source = "restauranteDTO", target = "restaurante", qualifiedByName = "transformarRestauranteToEntity")
    Turno toEntity(TurnoDTO dto);

    List<TurnoDTO> toDTO(List<Turno> listEntity);

    List<Turno> toEntity(List<TurnoDTO> listDTOs);

    @Named("transformarRestauranteToEntity")
    default Restaurante transformarRestaurante(RestauranteDTO dto){ return restauranteMapper.toEntity(dto);}
    @Named("transformarRestauranteToDTO")
    default RestauranteDTO transformarRestauranteDTO(Restaurante entity){ return restauranteMapper.toDTO(entity);}

}
