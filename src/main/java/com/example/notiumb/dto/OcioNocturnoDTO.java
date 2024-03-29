package com.example.notiumb.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class OcioNocturnoDTO {
    private Integer id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String cif;
    private Timestamp hora_apertura;
    private Timestamp hora_cierre;
    @NotBlank
    private String imagen_marca;
    @Valid
    private UserDTO userDTO;
    @Valid
    private DireccionDTO direccionDTO;
}
