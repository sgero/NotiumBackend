package com.example.notiumb.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CrearEventoDTO {
        private EventoDTO eventoDTO;
        private EntradaOcioDTO entradaOcioDTO;
        private ReservadoOcioDTO reservadoOcioDTO;
        private List<ListaOcioDTO> listaOcioDTO;
}
