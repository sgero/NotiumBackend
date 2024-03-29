package com.example.notiumb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="listas_ocio")
public class ListaOcio {

    private Integer id;

    private String nombre;

    private Double precio;

    private Double cantidad_venta;

    private Boolean activo;

    private Rpp rpp;
}
