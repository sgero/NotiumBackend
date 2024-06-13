package com.example.notiumb.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservaTiempoDTO {

    private Integer id_usuario;
    private String tipoReserva;
}
