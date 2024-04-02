package com.example.notiumb.dto;

import com.example.notiumb.model.Cliente;
import com.example.notiumb.model.ReservadoOcio;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ReservadoOcioClienteDTO {

    private Integer id;
    private String codigo;
    private Timestamp fecha_compra;
    private Integer cantidad_personas;
    private ClienteDTO clienteDTO;
    private ReservadoOcioDTO reservadoOcioDTO;
}
