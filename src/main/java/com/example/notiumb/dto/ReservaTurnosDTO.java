package com.example.notiumb.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReservaTurnosDTO {

    private Integer id_restaurante;
    private LocalDate fecha;

}
