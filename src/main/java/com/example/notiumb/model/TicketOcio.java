package com.example.notiumb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="ticket_ocio")
public class TicketOcio {

    private Integer id;

    private String codigo;

    private Boolean activo;

    private Evento evento;
}
