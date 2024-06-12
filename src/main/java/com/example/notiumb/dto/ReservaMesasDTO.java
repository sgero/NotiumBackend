package com.example.notiumb.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReservaMesasDTO {
    private RestauranteDTO restauranteDTO;
    private LocalDate fecha;
    private TurnoDTO turnoDTO;
}
