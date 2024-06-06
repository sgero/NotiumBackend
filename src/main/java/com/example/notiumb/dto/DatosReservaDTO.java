package com.example.notiumb.dto;


import jakarta.validation.Valid;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DatosReservaDTO {

    private LocalDate fecha;

    private Integer numPersonas;

    private RestauranteDTO restauranteDTO;

    private TurnoDTO turnoDTO;

    private UserDTO usuarioDTO ;

    private String codigoReserva;


}
