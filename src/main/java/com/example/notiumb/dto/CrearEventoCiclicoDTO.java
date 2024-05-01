package com.example.notiumb.dto;

import com.example.notiumb.model.enums.DiasARepetirCicloEventoOcio;
import com.example.notiumb.model.enums.RepetirCicloEventoOcio;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class CrearEventoCiclicoDTO {
    @Valid
    private EventoDTO eventoDTO;
    @Valid
    private EntradaOcioDTO entradaOcioDTO;
    @Valid
    private ReservadoOcioDTO reservadoOcioDTO;
    @Valid
    private List<ListaOcioDTO> listaOcioDTO;
    private RepetirCicloEventoOcio repetirCicloEventoOcio;
    private List<DiasARepetirCicloEventoOcio> diasARepetirCicloEventoOcioList;
}
