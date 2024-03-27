package com.example.notiumb.dto;

import com.example.notiumb.model.Usuario;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OcioDTO {

    private Integer id;
    private String nombre;
    private String cif;
    private LocalDateTime hora_apertura;
    private LocalDateTime hora_cierre;
    private String imagen_marca;
    private Boolean activo = true;
    private UsuarioDTO usuarioDTO;

}
