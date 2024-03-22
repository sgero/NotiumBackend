package com.example.notiumb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name="evento")
public class Evento {

    private Integer id;

    private String nombre;

    private String apellidos;

    private String dni;

    private LocalDateTime fecha_nacimiento;

    private String direccion;

    private Boolean activo;

    private Ocio ocio;
}
