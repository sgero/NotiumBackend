package com.example.notiumb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


//EJEMPLO DE ANOTACIONES DE JPA PARA MAPEAR ENTIDADES A TABLAS DE BASE DE DATOS DE FORMA AUTOMÁTICA
@Entity
@Table(name = "restaurante")
public class Restaurante {


    private Integer id;
    private String nombre;
    private String direccion;
    private String telefono;


}
