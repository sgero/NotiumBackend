package com.example.notiumb.model;


import com.example.notiumb.enums.TipoPromocion;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="promocion")
public class Promocion {

    private Integer id;

    private TipoPromocion tipo;

    private String titulo;

    private String foto;

    private Boolean activo;

    private Evento evento;

    private Restaurante restaurante;
}
