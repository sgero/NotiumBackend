package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cliente", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"user", "direccion", "entradaOcioClienteSet","listasOcioCliente","reservadosOcioCliente","reservas"})
public class Cliente {
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

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;



    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "id_direccion", nullable = false)
    private Direccion direccion;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<EntradaOcioCliente> entradaOcioClienteSet;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<ListaOcioCliente> listasOcioCliente;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<ReservadoOcioCliente> reservadosOcioCliente;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Reserva> reservas;

}
