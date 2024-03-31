package com.example.notiumb.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @OneToOne
    @JoinColumn(name = "id_direccion", nullable = false)
    private Direccion direccion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id_cliente", fetch = FetchType.LAZY)
    private Set<ListaOcioCliente> listasOcioCliente = new HashSet<>(0);

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id_reservado_ocio", fetch = FetchType.LAZY)
    private Set<Reserva> reservadosOcioCliente = new HashSet<>(0);

}
