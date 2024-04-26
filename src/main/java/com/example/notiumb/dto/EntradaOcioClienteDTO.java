package com.example.notiumb.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntradaOcioClienteDTO {
    private Integer id;
    @NotBlank
    private String codigo;
    private Double fechaCompra;
    @Valid
    private ClienteDTO clienteDTO;
    @Valid
    private EntradaOcioDTO entradaOcioDTO;
}
