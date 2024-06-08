package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ocio_nocturno", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"user", "direccion", "eventoSet", "rppSet"})
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
    private Time horaApertura;

    @Column(name = "hora_cierre", nullable = false)
    private Time horaCierre;
    @Column(name = "aforo", nullable = false)
    private Integer aforo;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "imagen_marca", nullable = false)
    private String imagenMarca;

    @Column(name = "activo")
    private Boolean activo = true;

    @Column(name = "verificado")
    private Boolean verificado = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_direccion", nullable = false)
    private Direccion direccion;

    @OneToMany(mappedBy = "ocioNocturno", fetch = FetchType.LAZY)
    private Set<Evento> eventoSet;

    @OneToMany(mappedBy = "ocioNocturno", fetch = FetchType.LAZY)
    private Set<Rpp> rppSet;

    @OneToMany(mappedBy = "ocioNocturno", fetch = FetchType.LAZY)
    private Set<ChatMensaje> chatMensajeSet;

}
