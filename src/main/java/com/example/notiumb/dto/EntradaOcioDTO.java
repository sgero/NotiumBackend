package com.example.notiumb.dto;

import com.example.notiumb.model.enums.Consumiciones;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntradaOcioDTO {
    private Integer id;
    @DecimalMin("0.99") @Digits(integer = 3, fraction = 2)
    private Double precio;
    private String detalleEntrada;
    private Consumiciones consumiciones;
    @Positive
    private Integer totalEntradas;
    @Valid
    private EventoDTO eventoDTO;
}
