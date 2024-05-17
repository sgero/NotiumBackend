package com.example.notiumb.dto;

import com.example.notiumb.model.enums.TipoPromocion;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PromocionDTO {
    private Integer id;
    private TipoPromocion tipoPromocion;
    private String titulo;
    private String foto;
}
