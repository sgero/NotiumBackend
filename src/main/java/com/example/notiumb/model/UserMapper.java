package com.example.notiumb.model;

import com.example.notiumb.converter.IUserMapper;
import com.example.notiumb.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper implements IUserMapper {
    @Override
    public UserDTO toDTO(User entity) {
        return null;
    }

    @Override
    public User toEntity(UserDTO dto) {
        return null;
    }

    @Override
    public List<UserDTO> toDTO(List<User> listEntity) {
        return null;
    }

    @Override
    public List<User> toEntity(List<UserDTO> listDTOs) {
        return null;
    }
}
