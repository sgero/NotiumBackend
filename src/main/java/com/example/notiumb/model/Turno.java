package com.example.notiumb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "turno")
public class Turno {

    private Integer id;

    private LocalDateTime hora_inicio;

    private LocalDateTime hora_fin;

    private Boolean activo;

    private Restaurante restaurante;



}
