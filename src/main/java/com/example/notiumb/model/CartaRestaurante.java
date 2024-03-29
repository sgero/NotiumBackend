package com.example.notiumb.model;

import jakarta.persistence.*;

@Entity
@Table(name="carta_rest")
public class CartaRestaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private Boolean activo;

//    private Restaurante restaurante;
}
