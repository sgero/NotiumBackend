package com.example.notiumb.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class ComprarReservadoDTO {
    private ReservadoOcioClienteDTO reservadoOcioClienteDTO;
    private List<DatosCompradorDTO> datosCompradorDTOS;
}
