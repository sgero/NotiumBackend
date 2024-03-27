package com.example.notiumb.model;

import jakarta.persistence.*;

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

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private User user;
}
