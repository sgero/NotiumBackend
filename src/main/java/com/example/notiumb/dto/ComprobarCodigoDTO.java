package com.example.notiumb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComprobarCodigoDTO {

    private String codigoReserva;

    private Integer id_restaurante;
}
