package com.example.notiumb.converter;


import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    static UserDTO toDTO(User entity) ;

    User toEntity(UserDTO dto);

    List<UserDTO> toDTO(List<User> listEntity);

    List<User> toEntity(List<UserDTO> listDTOs);
}
