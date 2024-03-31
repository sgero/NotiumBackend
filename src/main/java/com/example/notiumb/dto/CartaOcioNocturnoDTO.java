package com.example.notiumb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartaOcioNocturnoDTO {

    private Integer id;
    private Boolean activo;
    private OcioNocturnoDTO ocioNocturnoDTO;
}
