package com.example.notiumb.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DireccionDTO {
    private Integer id;
    @NotBlank
    private String calle;
    @NotBlank
    private Integer numero;
    @NotBlank
    private String puerta;
    @NotBlank @Digits(integer = 5, fraction = 0)
    private Integer codigoPostal;
    @NotBlank
    private String ciudad;
    @NotBlank
    private String provincia;
    @NotBlank
    private String pais;
}
