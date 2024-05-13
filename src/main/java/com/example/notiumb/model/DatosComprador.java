package com.example.notiumb.model;

import com.example.notiumb.model.enums.Genero;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "datos_comprador", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"reservadoOcioCliente", "entradaOcioCliente", "listaOcioCliente"})
public class DatosComprador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Timestamp fechaNacimiento;

    @Column(name = "genero", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Genero genero;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "reservado_ocio_cliente", nullable = false)
    private ReservadoOcioCliente reservadoOcioCliente;

    @OneToOne(mappedBy = "datosComprador", fetch = FetchType.LAZY)
    private EntradaOcioCliente entradaOcioCliente;

    @OneToOne(mappedBy = "datosComprador", fetch = FetchType.LAZY)
    private ListaOcioCliente listaOcioCliente;
}
