package com.example.notiumb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name="comentario")
public class Comentario {

    private Integer id;

    private String texto;

    private LocalDateTime fecha_comentario;

    private Boolean activo;

    private Restaurante restaurante;

    private Ocio ocio;

    private Cliente cliente;
}
