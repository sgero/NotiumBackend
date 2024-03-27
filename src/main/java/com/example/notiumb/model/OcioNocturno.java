package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ocio_nocturno", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OcioNocturno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "ocio_nocturno", fetch = FetchType.LAZY)
    private Set<Evento> eventoSet = new HashSet<>();

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cif")
    private String cif;
    @Column(name = "hora_apertura")
    private LocalDateTime hora_apertura;
    @Column(name = "hora_cierre")
    private LocalDateTime hora_cierre;
    @Column(name = "imagen_marca")
    private String imagen_marca;
    @Column(name = "activo")
    private Boolean activo = true;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private User user;



}
