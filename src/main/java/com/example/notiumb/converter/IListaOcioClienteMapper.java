package com.example.notiumb.converter;

import com.example.notiumb.dto.EntradaOcioClienteDTO;
import com.example.notiumb.dto.ListaOcioClienteDTO;
import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.dto.PromocionDTO;
import com.example.notiumb.model.EntradaOcioCliente;
import com.example.notiumb.model.ListaOcioCliente;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.model.Promocion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IListaOcioClienteMapper {
    IPromocionMapper promocionMapper = Mappers.getMapper(IPromocionMapper.class);
    @Mapping(source = "listaOcioDTO", target = "listaOcio")
    @Mapping(source = "clienteDTO", target = "cliente")
    @Mapping(source = "promocionDTO", target = "promocion", qualifiedByName = "conversorPromocionDTO")
    @Mapping(source = "datosCompradorDTO", target = "datosComprador")
    ListaOcioCliente toEntity(ListaOcioClienteDTO dto);

    @Mapping(source = "cliente", target = "clienteDTO")
    @Mapping(source = "listaOcio", target = "listaOcioDTO")
    @Mapping(source = "promocion", target = "promocionDTO", qualifiedByName = "conversorPromocionEntity")
    @Mapping(source = "datosComprador", target = "datosCompradorDTO")
    ListaOcioClienteDTO toDTO(ListaOcioCliente entity) ;

    List<ListaOcioClienteDTO> toDTO(List<ListaOcioCliente> listEntity);

    List<EntradaOcioCliente> toEntity(List<EntradaOcioClienteDTO> entradasOcioClienteLista);

    @Named("conversorPromocionDTO")
    default Promocion transformarPromocion(PromocionDTO dto){
        return promocionMapper.toEntity(dto);
    }

    @Named("conversorPromocionEntity")
    default PromocionDTO transformarPromocion(Promocion entity){
        return promocionMapper.toDTO(entity);
    }

}
