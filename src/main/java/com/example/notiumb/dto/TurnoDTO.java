package com.example.notiumb.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TurnoDTO {

    private Integer id;

    @NotBlank
    private LocalDateTime hora_inicio;

    @NotBlank
    private LocalDateTime hora_fin;

    private Boolean activo;

    @Valid
    private RestauranteDTO restauranteDTO;





}
