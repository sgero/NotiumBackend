package com.example.notiumb.dto;

import com.example.notiumb.model.Evento;
import com.example.notiumb.model.ListaOcioCliente;
import com.example.notiumb.model.Rpp;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListaOcioDTO {

    private Integer id;
    @NotBlank
    @PositiveOrZero
    private Double precio;
    @PositiveOrZero
    private Double total_invitaciones;
    private Boolean activo = true;
    @Valid
    private RppDTO rppDTO;
    @Valid
    private EventoDTO eventoDTO;
    @Valid
    private Set<ListaOcioClienteDTO> listasOcioClienteDTO;
}
