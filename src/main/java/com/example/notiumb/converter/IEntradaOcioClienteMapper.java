package com.example.notiumb.converter;

import com.example.notiumb.dto.DatosCompradorDTO;
import com.example.notiumb.dto.EntradaOcioClienteDTO;
import com.example.notiumb.dto.PromocionDTO;
import com.example.notiumb.model.DatosComprador;
import com.example.notiumb.model.EntradaOcioCliente;
import com.example.notiumb.model.Promocion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IEntradaOcioClienteMapper {
    IPromocionMapper promocionMapper = Mappers.getMapper(IPromocionMapper.class);
    IDatosCompradorMapper datosCompradorMapper = Mappers.getMapper(IDatosCompradorMapper.class);

    @Mapping(source = "entradaOcioDTO", target = "entradaOcio")
    @Mapping(source = "entradaOcioDTO.eventoDTO", target = "entradaOcio.evento")
    @Mapping(source = "clienteDTO", target = "cliente")
    @Mapping(source = "promocionDTO", target = "promocion", qualifiedByName = "conversorPromocionDTO")
    @Mapping(source = "datosCompradorDTO", target = "datosComprador", qualifiedByName = "conversorDatosCompradorDTO")
    EntradaOcioCliente toEntity(EntradaOcioClienteDTO dto);
    @Mapping(source = "cliente", target = "clienteDTO")
    @Mapping(source = "entradaOcio", target = "entradaOcioDTO")
    @Mapping(source = "entradaOcio.evento", target = "entradaOcioDTO.eventoDTO")
    @Mapping(source = "promocion", target = "promocionDTO", qualifiedByName = "conversorPromocionEntity")
    @Mapping(source = "datosComprador", target = "datosCompradorDTO", qualifiedByName = "conversorDatosCompradorEntity")
    EntradaOcioClienteDTO toDTO(EntradaOcioCliente entity);

    List<EntradaOcioCliente> toEntity(List<EntradaOcioClienteDTO> dtos);

    List<EntradaOcioClienteDTO> toDTO(List<EntradaOcioCliente> entities);

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
