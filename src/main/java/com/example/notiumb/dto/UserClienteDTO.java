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
public class UserClienteDTO {
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

    //Cliente
    @NotBlank
    private String nombre;

    @NotBlank
    private String apellidos;

    @NotBlank
    private String dni;

    @NotBlank
    private String telefono;

    @Past
    private Date fechaNacimiento;

    private String token_verificacion;

    @Valid
    private DireccionDTO direccionDTO;

}
