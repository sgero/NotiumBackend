package com.example.notiumb.model;

import com.example.notiumb.model.enums.TipoCategoria;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="producto")
public class Producto {

    private Integer id;

    private String nombre;

    private Double precio;

    private TipoCategoria tipoCategoria;

    private Boolean activo;

    private CartaOcio carta_ocio;

    private CartaRestaurante carta_res;

}
