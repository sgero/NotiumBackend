package com.example.notiumb.dto;

import com.example.notiumb.model.enums.Genero;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class DatosCompradorDTO {
    private Integer id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellidos;
    @NotBlank
    private String email;
    @Past
    private Date fechaNacimiento;
    private Genero genero;
    @Valid
    private ReservadoOcioClienteDTO reservadoOcioClienteDTO;
}
