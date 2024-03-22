package com.example.notiumb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva {

    private Integer id;

    private String codigo_reserva;

    private Boolean activo;

    private Cliente cliente;

    private Turno turno;

    private Restaurante restaurante;

    private Mesa mesa;

}
