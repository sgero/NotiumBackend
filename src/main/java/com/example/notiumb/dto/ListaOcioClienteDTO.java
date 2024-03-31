package com.example.notiumb.dto;

import com.example.notiumb.model.Cliente;
import com.example.notiumb.model.ListaOcio;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ListaOcioClienteDTO {

    private Integer id;
    private Timestamp fecha;
    private ClienteDTO clienteDTO;
    private ListaOcioDTO listaOcioDTO;
}
