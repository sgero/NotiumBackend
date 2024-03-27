package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="evento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evento {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tematica")
    private String tematica;
    @Column(name = "codigo_vestimenta")
    private String codigo_vestimenta;
    @Column(name = "edad")
    private Integer edad;
    @Column(name = "fecha")
    private LocalDateTime fecha;
    @Column(name = "aforo")
    private Integer aforo;
    @Column(name = "activo")
    private Boolean activo;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ocio", nullable = false)
    private Ocio ocio;
}
