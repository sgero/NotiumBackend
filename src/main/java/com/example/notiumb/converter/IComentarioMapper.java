package com.example.notiumb.converter;

import com.example.notiumb.dto.ClienteDTO;
import com.example.notiumb.dto.ComentarioDTO;
import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.model.Cliente;
import com.example.notiumb.model.Comentario;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.model.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IComentarioMapper {

    IRestauranteMapper irestauranteMapper = Mappers.getMapper(IRestauranteMapper.class);
    IOcioNocturnoMapper iocioNocturnoMapper = Mappers.getMapper(IOcioNocturnoMapper.class);
    IClienteMapper iclienteMapper = Mappers.getMapper(IClienteMapper.class);



    @Mapping(source = "restaurante", target = "restauranteDTO", qualifiedByName = "transformarRestaurante")
    @Mapping(source = "ocio", target = "ocioDTO", qualifiedByName = "transformarOcio")
//    @Mapping(source = "cliente", target = "clienteDTO", qualifiedByName = "transformarCliente")
    ComentarioDTO toDTO(Comentario entity);

    @Mapping(source = "restauranteDTO", target = "restaurante", qualifiedByName = "transformarRestauranteDTO")
    @Mapping(source = "ocioDTO", target = "ocio", qualifiedByName = "transformarOcioDTO")
//    @Mapping(source = "clienteDTO", target = "cliente", qualifiedByName = "transformarClienteDTO")
    Comentario toEntity(ComentarioDTO dto);

    List<Comentario> toEntity(List<ComentarioDTO> dtos);

    List<ComentarioDTO> toDTO(List<Comentario> entities);


    @Named("transformarRestaurante")
    default RestauranteDTO transformarRestaurante(Restaurante entity) { return irestauranteMapper.toDTO(entity);}

    @Named("transformarRestauranteDTO")
    default Restaurante transformarRestaurante(RestauranteDTO dto) { return irestauranteMapper.toEntity(dto);}

    @Named("transformarOcio")
    default OcioNocturnoDTO transformarOcio(OcioNocturno entity) { return iocioNocturnoMapper.toDTO(entity);}

    @Named("transformarOcioDTO")
    default OcioNocturno transformarOcio(OcioNocturnoDTO dto) { return  iocioNocturnoMapper.toEntity(dto);}
}
