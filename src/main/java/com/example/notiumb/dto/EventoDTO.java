package com.example.notiumb.dto;

import com.example.notiumb.model.Ocio;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EventoDTO {

    private Integer id;
    private String nombre;
    private String tematica;
    private String codigo_vestimenta;
    private Integer edad;
    private LocalDateTime fecha;
    private Integer aforo;
    private Boolean activo;
    private OcioDTO ocioDTO;
}
