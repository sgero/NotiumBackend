package com.example.notiumb.dto;

import com.example.notiumb.model.Evento;
import com.example.notiumb.model.ReservadoOcioCliente;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class ReservadoOcioDTO {

    private Integer id;
    private Double cantidad_venta;
    private Double total_entradas;
    private Boolean activo = true;
    private EventoDTO eventoDTO;
    private Set<ReservadoOcioClienteDTO> reservadosOcioClienteDTO;

}
