package com.example.notiumb.dto;

import com.example.notiumb.model.Direccion;
import com.example.notiumb.model.User;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestauranteDTO {

    private Integer id;


    private String nombre;


    private String telefono;


    private String hora_apertura;


    private String hora_cierre;


    private Boolean valoracion;


    private Boolean disponible;


    private String imagen_marca;


    private Boolean activo;


    @Valid
    private User user;

    @Valid
    private Direccion direccion;



}
