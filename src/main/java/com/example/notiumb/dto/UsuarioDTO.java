package com.example.notiumb.dto;

import com.example.notiumb.enums.Rol;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTO {

    private Integer id;
    private String username;
    private String email;
    private String password;
    private Rol rol;
    private boolean activo;
}
