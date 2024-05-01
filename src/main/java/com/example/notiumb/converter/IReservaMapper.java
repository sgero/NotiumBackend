package com.example.notiumb.converter;


import com.example.notiumb.dto.*;

import com.example.notiumb.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IReservaMapper {

    IClienteMapper clienteMapper = Mappers.getMapper(IClienteMapper.class);
    ITurnoMapper turnoMapper = Mappers.getMapper(ITurnoMapper.class);
    IRestauranteMapper restauranteMapper = Mappers.getMapper(IRestauranteMapper.class);
    IMesaMapper mesaMapper = Mappers.getMapper(IMesaMapper.class);

    @Mapping(source = "cliente", target = "clienteDTO", qualifiedByName = "transformarClienteToDTO")
    @Mapping(source = "turno", target = "turnoDTO", qualifiedByName = "transformarTurnoToDTO")
    @Mapping(source = "restaurante", target = "restauranteDTO", qualifiedByName = "transformarRestauranteToDTO")
    @Mapping(source = "mesa", target = "mesaDTO", qualifiedByName = "transformarMesaToDTO")
    ReservaDTO toDTO(Reserva entity) ;

    @Mapping(source = "clienteDTO", target = "cliente", qualifiedByName = "transformarClienteToEntity")
    @Mapping(source = "turnoDTO", target = "turno", qualifiedByName = "transformarTurnoToEntity")
    @Mapping(source = "restauranteDTO", target = "restaurante", qualifiedByName = "transformarRestauranteToEntity")
    @Mapping(source = "mesaDTO", target = "mesa", qualifiedByName = "transformarMesaToEntity")
    Reserva toEntity(ReservaDTO dto);

    List<ReservaDTO> toDTO(List<Reserva> listEntity);

    List<Reserva> toEntity(List<ReservaDTO> listDTOs);

    @Named("transformarClienteToEntity")
    default Cliente transformarCliente(ClienteDTO dto){ return clienteMapper.toEntity(dto);}
    @Named("transformarClienteToDTO")
    default ClienteDTO transformarClienteDTO(Cliente entity){ return clienteMapper.toDTO(entity);}

    @Named("transformarTurnoToEntity")
    default Turno transformarTurno(TurnoDTO dto){ return turnoMapper.toEntity(dto);}
    @Named("transformarTurnoToDTO")
    default TurnoDTO transformarTurnoDTO(Turno entity){ return turnoMapper.toDTO(entity);}


    @Named("transformarRestauranteToEntity")
    default Restaurante transformarRestaurante(RestauranteDTO dto){ return restauranteMapper.toEntity(dto);}
    @Named("transformarRestauranteToDTO")
    default RestauranteDTO transformarRestauranteDTO(Restaurante entity){ return restauranteMapper.toDTO(entity);}


    @Named("transformarMesaToEntity")
    default Mesa transformarMesa(MesaDTO dto){ return mesaMapper.toEntity(dto);}
    @Named("transformarMesaToDTO")
    default MesaDTO transformarMesaDTO(Mesa entity){ return mesaMapper.toDTO(entity);}
}
