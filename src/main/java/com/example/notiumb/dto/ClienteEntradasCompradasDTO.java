package com.example.notiumb.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClienteEntradasCompradasDTO {
    private List<EntradaOcioClienteDTO> entradasGeneralesCompradasPasadas;
    private List<EntradaOcioClienteDTO> entradasGeneralesCompradasFuturas;
    private List<ComprarReservadoDTO> reservadosCompradosPasados;
    private List<ComprarReservadoDTO> reservadosCompradosFuturos;
    private List<ListaOcioClienteDTO> listasCompradasPasadas;
    private List<ListaOcioClienteDTO> listasCompradasFuturas;
}
