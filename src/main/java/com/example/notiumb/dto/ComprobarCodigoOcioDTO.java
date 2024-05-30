package com.example.notiumb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComprobarCodigoOcioDTO {

    private String codigoReserva;

    private Integer id_ocio;
}
