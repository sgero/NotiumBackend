package com.example.notiumb.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.sql.Time;

@Data
@Builder
public class TurnoDTO {

    private Integer id;

    @NotBlank
    private Time hora_inicio;

    @NotBlank
    private Time hora_fin;

    private Boolean activo;
    @Valid
    private RestauranteDTO restauranteDTO;
}
