package com.example.notiumb.dto;

import com.example.notiumb.model.enums.TipoCategoria;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoDTO {

    private Integer id;

    private String nombre;

    private TipoCategoria tipoCategoria;

    private Boolean activo;


    private Integer idCartaOcio;

    private Integer idCartaRestaurante;

}
