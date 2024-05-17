package com.example.notiumb.converter;

import com.example.notiumb.dto.*;
import com.example.notiumb.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IListaOcioClienteMapper {
    IPromocionMapper promocionMapper = Mappers.getMapper(IPromocionMapper.class);
    IDatosCompradorMapper datosCompradorMapper = Mappers.getMapper(IDatosCompradorMapper.class);
    @Mapping(source = "listaOcioDTO", target = "listaOcio")
    @Mapping(source = "clienteDTO", target = "cliente")
    @Mapping(source = "promocionDTO", target = "promocion", qualifiedByName = "conversorPromocionDTO")
    @Mapping(source = "datosCompradorDTO", target = "datosComprador", qualifiedByName = "conversorDatosCompradorDTO")
    ListaOcioCliente toEntity(ListaOcioClienteDTO dto);

    @Mapping(source = "cliente", target = "clienteDTO")
    @Mapping(source = "listaOcio", target = "listaOcioDTO")
    @Mapping(source = "promocion", target = "promocionDTO", qualifiedByName = "conversorPromocionEntity")
    @Mapping(source = "datosComprador", target = "datosCompradorDTO", qualifiedByName = "conversorDatosCompradorEntity")
    ListaOcioClienteDTO toDTO(ListaOcioCliente entity) ;
    List<ListaOcioClienteDTO> toDTO(List<ListaOcioCliente> listEntity);
    List<ListaOcioCliente> toEntity(List<ListaOcioClienteDTO> entradasOcioClienteLista);

    @Named("conversorPromocionDTO")
    default Promocion transformarPromocion(PromocionDTO dto){
        return promocionMapper.toEntity(dto);
    }

    @Named("conversorPromocionEntity")
    default PromocionDTO transformarPromocion(Promocion entity){
        return promocionMapper.toDTO(entity);
    }
    @Named("conversorDatosCompradorDTO")
    default DatosComprador transformarDatosComprador(DatosCompradorDTO dto){
        return datosCompradorMapper.toEntity(dto);
    }

    @Named("conversorDatosCompradorEntity")
    default DatosCompradorDTO transformarDatosComprador(DatosComprador entity){
        return datosCompradorMapper.toDTO(entity);
    }

}
