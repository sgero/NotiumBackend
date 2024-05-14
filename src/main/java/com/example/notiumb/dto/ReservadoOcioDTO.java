package com.example.notiumb.dto;

import com.example.notiumb.model.enums.Botellas;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservadoOcioDTO {

    private Integer id;
    @PositiveOrZero
    private Integer reservadosDisponibles;
    @Positive
    private Integer personasMaximasPorReservado;
    @Digits(integer = 3, fraction = 2)
    private Double precio;
    private String detalleReservado;
    private Botellas botellas;
    @Valid
    private EventoDTO eventoDTO;

}
