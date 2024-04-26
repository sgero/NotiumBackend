package com.example.notiumb.converter;



import com.example.notiumb.dto.FormatoDTO;
import com.example.notiumb.dto.ProductoDTO;
import com.example.notiumb.dto.ProductoFormatoDTO;

import com.example.notiumb.model.Formato;
import com.example.notiumb.model.Producto;
import com.example.notiumb.model.ProductoFormato;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductoFormatoMapper {

    IProductoMapper productoMapper = Mappers.getMapper(IProductoMapper.class);
    IFormatoMapper formatoMapper = Mappers.getMapper(IFormatoMapper.class);

    @Mapping(source = "productoDTO", target = "producto", qualifiedByName = "transformarProductoAEntity")
    @Mapping(source = "formatoDTO", target = "formato", qualifiedByName = "transformarFormatoAEntity")
    ProductoFormato toEntity(ProductoFormatoDTO dto);

    @Mapping(source = "producto", target = "productoDTO", qualifiedByName = "transformarProductoADTO")
    @Mapping(source = "formato", target = "formatoDTO", qualifiedByName = "transformarFormatoADTO")
    ProductoFormatoDTO toDTO(ProductoFormato entity);

    List<ProductoFormatoDTO> toDTO(List<ProductoFormato> listEntity);

    List<ProductoFormato> toEntity(List<ProductoFormatoDTO> listDTOs);

    @Named("transformarProductoAEntity")
    default Producto transformarUser(ProductoDTO dto){
        return productoMapper.toEntity(dto);
    }

    @Named("transformarProductoADTO")
    default ProductoDTO transformarUser(Producto entity) { return productoMapper.toDTO(entity);
    }

    @Named("transformarFormatoAEntity")
    default Formato transformarDireccion(FormatoDTO dto){
        return formatoMapper.toEntity(dto);
    }

    @Named("transformarFormatoADTO")
    default FormatoDTO transformarDireccion(Formato entity){
        return formatoMapper.toDTO(entity);
    }
}
