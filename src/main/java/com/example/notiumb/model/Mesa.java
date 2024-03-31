package com.example.notiumb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mesa")
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private Integer num_plazas;

    private Boolean reservada;

    private Boolean activo;

//    private Restaurante restaurante;


}
