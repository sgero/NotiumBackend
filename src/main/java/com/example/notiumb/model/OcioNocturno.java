package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ocio_nocturno", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"user", "direccion"})
public class OcioNocturno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "cif", nullable = false)
    private String cif;

    @Column(name = "hora_apertura", nullable = false)
    private LocalDateTime hora_apertura;

    @Column(name = "hora_cierre", nullable = false)
    private LocalDateTime hora_cierre;

    @Column(name = "imagen_marca", nullable = false)
    private String imagen_marca;

    @Column(name = "activo")
    private Boolean activo = true;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_usuario", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_direccion", nullable = false)
    private Direccion direccion;

}
