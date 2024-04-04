package com.example.notiumb.dto;

import com.example.notiumb.model.enums.TipoBebida;
import com.example.notiumb.model.enums.TipoPlatoRestaurante;
import lombok.Data;

@Data
public class ProductoTipoPlatoDTO {

    private Integer id;


    private Double precio;


    private Integer idProducto;


    private TipoPlatoRestaurante tipoPlatoRestaurante;
}
