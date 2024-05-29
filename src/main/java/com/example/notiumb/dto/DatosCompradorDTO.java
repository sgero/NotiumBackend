package com.example.notiumb.dto;

import com.example.notiumb.model.enums.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
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
    private String telefono;
    @Past
    private Timestamp fechaNacimiento;
    private Genero genero;
    private ReservadoOcioClienteDTO reservadoOcioClienteDTO;
}
