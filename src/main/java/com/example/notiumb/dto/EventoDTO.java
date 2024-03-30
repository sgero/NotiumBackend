package com.example.notiumb.dto;

import com.example.notiumb.model.enums.CodigoVestimentaOcio;
import com.example.notiumb.model.enums.EdadMinimaOcio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class EventoDTO {
    private Integer id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String tematica;
    @FutureOrPresent
    private Timestamp fecha;
    private CodigoVestimentaOcio codigoVestimentaOcio;
    private EdadMinimaOcio edadMinimaOcio;
    @Positive
    private Integer aforo;
    @Valid
    private OcioNocturnoDTO ocioNocturnoDTO;
}
