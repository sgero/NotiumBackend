package com.example.notiumb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="carta_rest")
public class CartaRestaurante {

    private Integer id;

    private Boolean activo;

    private Restaurante restaurante;
}
