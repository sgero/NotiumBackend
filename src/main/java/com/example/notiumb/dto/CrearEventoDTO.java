package com.example.notiumb.dto;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CrearEventoDTO {
        @Valid
        private EventoDTO eventoDTO;
        @Valid
        private EntradaOcioDTO entradaOcioDTO;
        @Valid
        private ReservadoOcioDTO reservadoOcioDTO;
        @Valid
        private List<ListaOcioDTO> listaOcioDTO;
}
