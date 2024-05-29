package com.example.notiumb.dto;

import com.example.notiumb.model.enums.Rol;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
@Builder
public class UserOcioNocturnoDTO {
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

    //Ocio Nocturno
    @NotBlank
    private String nombre;
    @NotBlank
    private String cif;

    private String telefono;

    private Time horaApertura;

    private Time horaCierre;

    @NotBlank
    private Integer aforo;

    @NotBlank
    private String imagenMarca;
    @Valid
    private UserDTO userDTO;
    @Valid
    private DireccionDTO direccionDTO;

}
