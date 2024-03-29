package com.example.notiumb.model;


import com.example.notiumb.model.enums.TipoPromocion;
import jakarta.persistence.*;

@Entity
@Table(name="promocion")
public class Promocion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private TipoPromocion tipo;

    private String titulo;

    private String foto;

    private Boolean activo;

//    private Evento evento;
//
//    private Restaurante restaurante;
}
