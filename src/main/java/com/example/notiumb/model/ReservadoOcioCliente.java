package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name="reservado_ocio_cliente", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"cliente", "reservadoOcio","datosCompradors"})
public class ReservadoOcioCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Column(name = "fecha_compra", nullable = false)
    private Timestamp fecha_compra;

    @Column(name = "cantidad_personas", nullable = false)
    private Integer cantidad_personas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservado_ocio", nullable = false)
    private ReservadoOcio reservadoOcio;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_promocion")
    private Promocion promocion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservadoOcioCliente", fetch = FetchType.LAZY)
    private Set<DatosComprador> datosCompradors;
}
