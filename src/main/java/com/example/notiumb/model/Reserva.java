package com.example.notiumb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String codigo_reserva;

    private Boolean activo;

//    private Cliente cliente;
//
//    private Turno turno;
//
//    private Restaurante restaurante;
//
//    private Mesa mesa;

}
