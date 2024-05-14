package com.example.notiumb.dto;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InformacionTiposEntradasEvento {
    private int id;
    @Valid
    private EventoDTO eventoDTO;
    @Valid
    private EntradaOcioDTO entradaOcioDTO;
    @Valid
    private ReservadoOcioDTO reservadoOcioDTO;
    private ListaOcioDTO listaOcioDTO;
}
