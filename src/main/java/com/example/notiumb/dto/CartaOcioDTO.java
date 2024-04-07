package com.example.notiumb.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartaOcioDTO {

    private Integer id;
    private Boolean activo;
    @Valid
    private OcioNocturnoDTO ocioNocturnoDTO;
}
