package com.example.notiumb.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ClienteDTO {
    private Integer id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellidos;
    @NotBlank
    private String dni;
    @Past
    private Date fechaNacimiento;
    @Valid
    private UserDTO userDTO;
    @Valid
    private DireccionDTO direccionDTO;
}
