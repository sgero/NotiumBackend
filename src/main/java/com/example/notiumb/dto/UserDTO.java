package com.example.notiumb.dto;


import com.example.notiumb.model.enums.Rol;
import lombok.Data;

@Data
public class UserDTO {

    private Integer id;

    private String username;

    private String password;

    private Rol rol;

}