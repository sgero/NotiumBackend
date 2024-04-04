package com.example.notiumb.dto;

import com.example.notiumb.model.Producto;
import com.example.notiumb.model.enums.TipoBebida;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class ProductoTipoBebidaDTO {


    private Integer id;


    private Double precio;


    private Integer idProducto;


    private TipoBebida tipoBebida;
}
