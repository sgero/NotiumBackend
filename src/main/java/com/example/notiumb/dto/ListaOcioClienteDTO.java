package com.example.notiumb.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
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
    private PromocionDTO promocionDTO;
    @Valid
    private DatosCompradorDTO datosCompradorDTO;
}
