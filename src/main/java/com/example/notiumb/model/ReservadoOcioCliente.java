package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@Entity
@Table(name="reservado_ocio_cliente", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "id_reservado_ocio", nullable = false)
    private ReservadoOcio reservadoOcio;
}