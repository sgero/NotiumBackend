package com.example.notiumb.dto;

import com.example.notiumb.model.ListaOcio;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
@Builder
public class RppDTO {

    private Integer id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String telefono;
    private Timestamp fecha_nacimiento;
    private UserDTO userDTO;
    private DireccionDTO direccionDTO;

}
