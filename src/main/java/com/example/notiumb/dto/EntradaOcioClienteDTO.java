package com.example.notiumb.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class EntradaOcioClienteDTO {
    private Integer id;
    @NotBlank
    private String codigo;
    private Timestamp fechaCompra;
    @Valid
    private ClienteDTO clienteDTO;
    @Valid
    private EntradaOcioDTO entradaOcioDTO;
    private PromocionDTO promocionDTO;
    @Valid
    private DatosCompradorDTO datosCompradorDTO;
}
