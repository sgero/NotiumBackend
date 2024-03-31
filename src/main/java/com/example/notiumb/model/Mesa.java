package com.example.notiumb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "mesa")
public class Mesa {

    private Integer id;

    private Integer num_plazas;

    private Boolean reservada;

    private Boolean activo;

    private Restaurante restaurante;


}
