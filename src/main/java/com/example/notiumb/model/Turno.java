package com.example.notiumb.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "turno")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private LocalDateTime hora_inicio;

    private LocalDateTime hora_fin;

    private Boolean activo;

//    private Restaurante restaurante;



}
