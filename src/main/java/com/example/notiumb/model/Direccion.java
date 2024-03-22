package com.example.notiumb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "direccion")

public class Direccion{

    private Integer id;

    private String calle;

    private Integer cp;

    private String ciudad;

    private String estado_o_provincia;

    private String pais;

    private Restaurante restaurante;

    private Ocio ocio;

    private Cliente cliente;




}


