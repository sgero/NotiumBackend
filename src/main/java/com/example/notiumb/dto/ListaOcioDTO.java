package com.example.notiumb.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListaOcioDTO {

    private Integer id;
    @NotBlank
    @PositiveOrZero
    private Double precio;
    @PositiveOrZero
    private Double total_invitaciones;
    @Valid
    private RppDTO rppDTO;
    @Valid
    private EventoDTO eventoDTO;
}
