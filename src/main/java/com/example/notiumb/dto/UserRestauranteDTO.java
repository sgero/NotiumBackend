package com.example.notiumb.dto;

import com.example.notiumb.model.enums.Rol;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserRestauranteDTO {
    private Integer id;
    //User
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email
    private String email;

    private boolean activo;

    private Rol rol;


    //Restaurante
    private String nombre;

    private String telefono;

    @NotBlank
    private String cif;

    private String hora_apertura;

    private String hora_cierre;

    private Boolean valoracion;

    private Boolean disponible;

    private String imagen_marca;

    @Valid
    private UserDTO userDTO;

    @Valid
    private DireccionDTO direccionDTO;

}
