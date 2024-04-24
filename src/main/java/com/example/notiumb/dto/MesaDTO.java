package com.example.notiumb.dto;


import com.example.notiumb.model.Restaurante;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MesaDTO {

    private Integer id;


    private Integer num_plazas;


    private Boolean reservada;


    private Boolean activo;

    @Valid
    private RestauranteDTO restauranteDTO;
}
