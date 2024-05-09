package com.example.notiumb.converter;


import com.example.notiumb.dto.CartaOcioDTO;
import com.example.notiumb.dto.CartaRestauranteDTO;
import com.example.notiumb.dto.FormatoDTO;
import com.example.notiumb.dto.ProductoDTO;
import com.example.notiumb.model.CartaOcio;
import com.example.notiumb.model.CartaRestaurante;
import com.example.notiumb.model.Formato;
import com.example.notiumb.model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductoMapper {

    ICartaRestauranteMapper cartaRestauranteMapper = Mappers.getMapper(ICartaRestauranteMapper.class);
    ICartaOcioMapper cartaOcioMapper = Mappers.getMapper(ICartaOcioMapper.class);

    @Mapping(source = "cartaRes", target = "cartaRes", qualifiedByName = "transformarCRAEntity")
    @Mapping(source = "cartaOcio", target = "cartaOcio", qualifiedByName = "transformarCOAEntity")
    Producto toEntity(ProductoDTO dto);

    @Mapping(source = "cartaRes", target = "cartaRes", qualifiedByName = "transformarCRADTO")
    @Mapping(source = "cartaOcio", target = "cartaOcio", qualifiedByName = "transformarFormatoADTO")
    ProductoDTO toDTO(Producto entity);

    List<ProductoDTO> toDTO(List<Producto> listEntity);

    List<Producto> toEntity(List<ProductoDTO> listDTOs);

    @Named("transformarCRAEntity")
    default CartaRestaurante transformarCRToEntity(CartaRestauranteDTO dto){
        return cartaRestauranteMapper.toEntity(dto);
    }

    @Named("transformarCRADTO")
    default CartaRestauranteDTO transformarCRToDTO(CartaRestaurante entity) { return cartaRestauranteMapper.toDTO(entity);
    }

    @Named("transformarCOAEntity")
    default CartaOcio transformarCOToEntity(CartaOcioDTO dto){
        return cartaOcioMapper.toEntity(dto);
    }

    @Named("transformarFormatoADTO")
    default CartaOcioDTO transformarCOToDTO(CartaOcio entity){
        return cartaOcioMapper.toDTO(entity);
    }

}
