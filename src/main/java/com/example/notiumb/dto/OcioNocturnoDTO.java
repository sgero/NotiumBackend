package com.example.notiumb.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OcioNocturnoDTO {
    private Integer id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String cif;
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
