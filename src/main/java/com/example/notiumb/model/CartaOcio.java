package com.example.notiumb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="carta_ocio")
public class CartaOcio {

    private Integer id;

    private Boolean activo;

    private Ocio ocio;
}
