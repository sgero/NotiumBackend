package com.example.notiumb.converter;

import com.example.notiumb.dto.DireccionDTO;
import com.example.notiumb.dto.EventoDTO;
import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.model.Direccion;
import com.example.notiumb.model.Evento;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOcioNocturnoMapper {
    IUserMapper userMapper = Mappers.getMapper(IUserMapper.class);
    IDireccionMapper direccionMapper = Mappers.getMapper(IDireccionMapper.class);

    @Mapping(source = "userDTO", target = "user" , qualifiedByName = "transformarUserDTO")
    @Mapping(source = "direccionDTO", target = "direccion" , qualifiedByName = "transformarDireccionDTO")
    OcioNocturno toEntity(OcioNocturnoDTO dto);
    @Mapping(source = "user", target = "userDTO" , qualifiedByName = "transformarUser")
    @Mapping(source = "direccion", target = "direccionDTO" , qualifiedByName = "transformarDireccion")
    OcioNocturnoDTO toDTO(OcioNocturno entity);

    List<OcioNocturno> toEntity(List<OcioNocturnoDTO> dtos);

    List<OcioNocturnoDTO> toDTO(List<OcioNocturno> entities);

    @Named("transformarUserDTO")
    default User transformarUser(UserDTO dto){
        return userMapper.toEntity(dto);
    }

    @Named("transformarUser")
    default UserDTO transformarUser(User entity){
        return userMapper.toDTO(entity);
    }

    @Named("transformarDireccionDTO")
    default Direccion transformarDireccion(DireccionDTO dto){
        return direccionMapper.toEntity(dto);
    }

    @Named("transformarDireccion")
    default DireccionDTO transformarDireccion(Direccion entity){
        return direccionMapper.toDTO(entity);
    }
}
