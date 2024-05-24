package com.example.notiumb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CantidadesRestantesDTO {
    private Integer aforoEvento;
    private Integer entradasVendidas;
    private Integer reservadosVendidos;
    private Integer personasTotalesReservadosVendidos;
    private Integer invitacionesTotalesLista;
    private Integer clientesApuntadosALista;
    private Integer totalAsistentes;
}
