package com.example.notiumb.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

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
    private OcioNocturnoDTO ocioNocturnoDTO;

}
