package com.example.notiumb.model;

import com.example.notiumb.enums.TipoProducto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="producto")
public class Producto {

    private Integer id;

    private String nombre;

    private Double precio;

    private TipoProducto tipo_producto;

    private Boolean activo;

    private CartaOcio carta_ocio;

    private CartaRestaurante carta_res;

}
