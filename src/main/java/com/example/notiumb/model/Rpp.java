package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rpp", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rpp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "apellidos", nullable = false)
    private String apellidos;
    @Column(name = "dni", nullable = false)
    private String dni;
    @Column(name = "telefono", nullable = false)
    private String telefono;
    @Column(name = "fecha_nacimiento", nullable = false)
    private Timestamp fecha_nacimiento;
    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_direccion", nullable = false)
    private Direccion direccion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lista_ocio", fetch = FetchType.LAZY)
    private Set<ListaOcio> listasOcio = new HashSet<>(0);
}
