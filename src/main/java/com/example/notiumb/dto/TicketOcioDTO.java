package com.example.notiumb.dto;

import com.example.notiumb.model.Evento;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketOcioDTO {
    private Integer id;
    private String codigo;
    private Boolean activo;
    private EventoDTO eventoDTO;
}
