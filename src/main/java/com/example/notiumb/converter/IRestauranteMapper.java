package com.example.notiumb.converter;


import com.example.notiumb.dto.DireccionDTO;
import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.model.Direccion;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IRestauranteMapper {
    IUserMapper userMapper = Mappers.getMapper(IUserMapper.class);
    IDireccionMapper direccionMapper = Mappers.getMapper(IDireccionMapper.class);

    RestauranteDTO toDTO(Restaurante entity);

    Restaurante toEntity(RestauranteDTO dto);

    List<Restaurante> toEntity(List<RestauranteDTO> dtos);

    List<RestauranteDTO> toDTO(List<Restaurante> entities);

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
