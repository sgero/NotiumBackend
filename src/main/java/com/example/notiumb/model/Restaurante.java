package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//EJEMPLO DE ANOTACIONES DE JPA PARA MAPEAR ENTIDADES A TABLAS DE BASE DE DATOS DE FORMA AUTOMÁTICA
@Entity
@Table(name = "restaurante")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Restaurante {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "cif", nullable = false)
    private String cif;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "hora_apertura", nullable = false)
    private String hora_apertura;

    @Column(name = "hora_cierre", nullable = false)
    private String hora_cierre;

    @Column(name = "valoracion")
    private Boolean valoracion;

    @Column(name = "disponible")
    private Boolean disponible;

    @Column(name = "imagen_marca", nullable = false)
    private String imagen_marca;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "id_usuario", nullable = false)
    private Integer id_usuario;


}
