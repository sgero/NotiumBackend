package com.example.notiumb.dto;

import com.example.notiumb.model.Cliente;
import com.example.notiumb.model.ListaOcio;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListaOcioClienteDTO {

    private Integer id;
    @FutureOrPresent
    private Timestamp fecha;
    @Valid
    private ClienteDTO clienteDTO;
    @Valid
    private ListaOcioDTO listaOcioDTO;
}
