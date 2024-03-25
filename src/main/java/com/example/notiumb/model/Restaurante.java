package com.example.notiumb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.time.LocalTime;


//EJEMPLO DE ANOTACIONES DE JPA PARA MAPEAR ENTIDADES A TABLAS DE BASE DE DATOS DE FORMA AUTOMÁTICA
@Entity
@Table(name = "restaurante")
public class Restaurante {


    private Integer id;

    private String nombre;

    private String cif;

    private String direccion;

    private String telefono;

    private LocalDateTime hora_apertura;

    private LocalDateTime hora_cierre;

    private Boolean valoracion;

    private Boolean disponible;

    private String imagen_marca;

    private Boolean activo;

    private User usuario;




}
