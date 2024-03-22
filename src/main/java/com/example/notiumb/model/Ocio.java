package com.example.notiumb.model;

import io.swagger.models.auth.In;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "ocio_nocturno")
public class Ocio {

    private Integer id;

    private String nombre;

    private String cif;

    private LocalDateTime hora_apertura;

    private LocalDateTime hora_cierre;

    private String imagen_marca;

    private Boolean activo;

    private User usuario;



}
