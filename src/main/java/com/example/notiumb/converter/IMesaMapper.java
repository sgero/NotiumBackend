package com.example.notiumb.converter;


import com.example.notiumb.dto.MesaDTO;
import com.example.notiumb.dto.ProductoDTO;
import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.model.Mesa;
import com.example.notiumb.model.Producto;
import com.example.notiumb.model.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMesaMapper {

    IRestauranteMapper restauranteMapper = Mappers.getMapper(IRestauranteMapper.class);

    @Mapping(source = "restauranteDTO", target = "restaurante", qualifiedByName = "transformarRestauranteToEntity")
    Mesa toEntity(MesaDTO dto);

    @Mapping(source = "restaurante", target = "restauranteDTO", qualifiedByName = "transformarRestauranteToDTO")
    MesaDTO toDTO(Mesa entity);

    List<MesaDTO> toDTO(List<Mesa> listEntity);

    List<Mesa> toEntity(List<MesaDTO> listDTOs);


    @Named("transformarRestauranteToEntity")
    default Restaurante transformarRestaurante(RestauranteDTO dto){ return restauranteMapper.toEntity(dto);}
    @Named("transformarRestauranteToDTO")
    default RestauranteDTO transformarRestauranteDTO(Restaurante entity){ return restauranteMapper.toDTO(entity);}


}
