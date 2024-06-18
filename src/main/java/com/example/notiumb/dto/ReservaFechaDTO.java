package com.example.notiumb.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class ReservaFechaDTO {
    private RestauranteDTO restauranteDTO;
    private LocalDate fecha;
}
