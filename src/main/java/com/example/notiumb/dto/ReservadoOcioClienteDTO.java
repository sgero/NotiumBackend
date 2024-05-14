package com.example.notiumb.dto;

import com.example.notiumb.model.Cliente;
import com.example.notiumb.model.ReservadoOcio;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservadoOcioClienteDTO {

    private Integer id;
    @NotBlank
    private String codigo;
    @FutureOrPresent
    private Timestamp fecha_compra;
    @Positive
    private Integer cantidad_personas;
    @Valid
    private ClienteDTO clienteDTO;
    @Valid
    private ReservadoOcioDTO reservadoOcioDTO;
    @Valid
    private PromocionDTO promocionDTO;
}
