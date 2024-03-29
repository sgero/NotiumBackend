package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="comentario")
@Data
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String texto;

    private LocalDateTime fecha_comentario;

    private Boolean activo;

//    private Restaurante restaurante;
//
//    private OcioNocturno ocio;
//
//    private Cliente cliente;
}
