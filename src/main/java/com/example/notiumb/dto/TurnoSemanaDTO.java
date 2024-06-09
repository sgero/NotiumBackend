package com.example.notiumb.dto;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TurnoSemanaDTO {

    private Integer id;

    private Integer diaSemana;

    @Valid
    private TurnoDTO turnoDTO;
}
