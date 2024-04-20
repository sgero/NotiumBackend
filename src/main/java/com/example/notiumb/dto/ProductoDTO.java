package com.example.notiumb.dto;

import com.example.notiumb.model.enums.TipoCategoria;
import lombok.Data;

@Data
public class ProductoDTO {

    private Integer id;
    private String nombre;
    private TipoCategoria tipoCategoria;
    private Boolean activo;

    private Integer idCartaOcio;
    private Integer idCartaRestaurante;
    private ProductoTipoBebidaDTO productoTipoBebida;
    private ProductoTipoPlatoDTO productoTipoPlato;
}
