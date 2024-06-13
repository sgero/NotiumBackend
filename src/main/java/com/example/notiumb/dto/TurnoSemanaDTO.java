package com.example.notiumb.dto;

import com.example.notiumb.model.enums.DiasARepetirCicloEventoOcio;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TurnoSemanaDTO {

    private List<DiasARepetirCicloEventoOcio> diaSemana;

    @Valid
    private TurnoDTO turnoDTO;
}
