package com.example.notiumb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="reservados_ocio")
public class ReservadoOcio {

    private Integer id;

    private String nombre;

    private Double precio;

    private Double cantidad_venta;

    private Boolean activo;

    private TicketOcio ticket_ocio;

}
