package com.example.notiumb.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="ticket_ocio")
public class EntradaNormalOcio {

    private Integer id;

    private Double precio;

    private Double cantidad_venta;

    private Boolean activo;

    private TicketOcio ticket_ocio;
}
