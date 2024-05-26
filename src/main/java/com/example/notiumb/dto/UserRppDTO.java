package com.example.notiumb.dto;

import com.example.notiumb.model.enums.Rol;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class UserRppDTO {

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
    //Rpp
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellidos;
    @NotBlank
    private String dni;
    @NotBlank
    private String telefono;
    @NotBlank
    private Timestamp fechaNacimiento;
    @Valid
    private UserDTO userDTO;
    @Valid
    private DireccionDTO direccionDTO;
    @Valid
    private OcioNocturnoDTO ocioNocturnoDTO;
}
