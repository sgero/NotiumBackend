package com.example.notiumb.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestauranteDTO {

    private Integer id;


    private String nombre;


    private String telefono;

    @NotBlank
    private String cif;

    private String hora_apertura;


    private String hora_cierre;


    private Boolean valoracion;


    private Boolean disponible;


    private String imagen_marca;


    private Boolean activo;


    @Valid
    private UserDTO userDTO;

    @Valid
    private DireccionDTO direccionDTO;


}
