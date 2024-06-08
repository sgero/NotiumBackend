package com.example.notiumb.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ChatMensajeDTO {
    private Integer id;
    private String texto;
    private Timestamp fecha;
    private OcioNocturnoDTO ocioNocturnoDTO;
    private EventoDTO chatEventoDTO;
    private Boolean editado;
}
