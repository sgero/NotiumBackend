package com.example.notiumb.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FormatoDTO {
    private Integer id;

    @NotBlank
    private String formato;
}
