package com.example.notiumb.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ComentarioDTO {

    private Integer id;

    private String texto;

    private Timestamp fecha_comentario;

    @NotBlank
    @Digits(integer = 2, fraction = 0)
    private String codigoReserva;

    private Integer valoracion;

    private Boolean activo;

    @Valid
    private RestauranteDTO restauranteDTO;

    @Valid
    private OcioNocturnoDTO ocioDTO;

}

