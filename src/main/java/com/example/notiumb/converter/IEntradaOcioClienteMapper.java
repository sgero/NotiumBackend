package com.example.notiumb.converter;

import com.example.notiumb.dto.EntradaOcioClienteDTO;
import com.example.notiumb.dto.PromocionDTO;
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

    @Mapping(source = "entradaOcioDTO", target = "entradaOcio")
    @Mapping(source = "clienteDTO", target = "cliente")
    @Mapping(source = "promocionDTO", target = "promocion", qualifiedByName = "conversorPromocionDTO")
    @Mapping(source = "datosCompradorDTO", target = "datosComprador")
    EntradaOcioCliente toEntity(EntradaOcioClienteDTO dto);
    @Mapping(source = "cliente", target = "clienteDTO")
    @Mapping(source = "entradaOcio", target = "entradaOcioDTO")
    @Mapping(source = "promocion", target = "promocionDTO", qualifiedByName = "conversorPromocionEntity")
    @Mapping(source = "datosComprador", target = "datosCompradorDTO")
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
}
