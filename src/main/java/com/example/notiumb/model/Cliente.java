package com.example.notiumb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "cliente")
public class Cliente {

    private Integer id;

    private String nombre;

    private String apellidos;

    private String dni;

    private Date fecha_nacimiento;

    private String ubicacion_actual;

    private Boolean activo;

    private Usuario usuario;
}
