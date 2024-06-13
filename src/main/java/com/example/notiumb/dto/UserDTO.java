package com.example.notiumb.dto;


import com.example.notiumb.model.enums.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Email
    private String email;

    private boolean activo;

    private boolean verificado;

    private Rol rol;
}
