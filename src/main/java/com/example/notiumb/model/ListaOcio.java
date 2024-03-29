package com.example.notiumb.model;

import jakarta.persistence.*;

@Entity
@Table(name="listas_ocio")
public class ListaOcio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String nombre;

    private Double precio;

    private Double cantidad_venta;

    private Boolean activo;

//    private Rpp rpp;
}
