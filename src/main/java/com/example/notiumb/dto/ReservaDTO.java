package com.example.notiumb.dto;

import com.example.notiumb.model.Cliente;
import com.example.notiumb.model.Mesa;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.Turno;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;

public class ReservaDTO {


    private Integer id;


    private String codigo_reserva;


    private Boolean activo;

    @Valid
    private ClienteDTO clienteDTO;

    @Valid
    private TurnoDTO turnoDTO;

    @Valid
    private RestauranteDTO restauranteDTO;
    @Valid

    private MesaDTO mesaDTO;
}
