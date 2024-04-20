package com.example.notiumb.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RppDTO {

    private Integer id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellidos;
    @NotBlank
    private String dni;
    @NotBlank
    private String telefono;
    @NotBlank
    private Timestamp fecha_nacimiento;
    @Valid
    private UserDTO userDTO;
    @Valid
    private DireccionDTO direccionDTO;
    @Valid
    private OcioNocturnoDTO ocioNocturnoDTO;

}
