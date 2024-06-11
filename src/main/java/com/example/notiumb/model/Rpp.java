package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "rpp", schema = "notium", catalog = "postgres")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"user", "direccion","ocioNocturno", "listasOcio"})
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
    private Timestamp fechaNacimiento;
    @Column(name = "activo")
    private Boolean activo = true;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_direccion", nullable = false)
    private Direccion direccion;

    @OneToMany(mappedBy = "rpp")
    private Set<ListaOcio> listasOcio;

    @ManyToOne
    @JoinColumn(name = "id_ocio_nocturno", nullable = false)
    private OcioNocturno ocioNocturno;
}
