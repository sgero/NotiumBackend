package com.example.notiumb.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.sql.Time;

@Data
@Builder
public class OcioNocturnoDTO {
    private Integer id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String cif;
    private Time horaApertura;
    private Time horaCierre;
    @NotBlank
    private String imagenMarca;
    @Valid
    private UserDTO userDTO;
    @Valid
    private DireccionDTO direccionDTO;
}
