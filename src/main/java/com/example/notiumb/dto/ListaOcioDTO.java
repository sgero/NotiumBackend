package com.example.notiumb.dto;

import com.example.notiumb.model.Evento;
import com.example.notiumb.model.ListaOcioCliente;
import com.example.notiumb.model.Rpp;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ListaOcioDTO {

    private Integer id;
    private Double precio;
    private Double total_invitaciones;
    private Boolean activo = true;
    private RppDTO rppDTO;
    private EventoDTO eventoDTO;
    private Set<ListaOcioClienteDTO> listasOcioClienteDTO;
}
