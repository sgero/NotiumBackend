package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="comentario", schema = "notium", catalog = "postgres")
@Data
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "texto")
    private String texto;

    @Column(name = "fecha_comentario")
    private Timestamp fecha_comentario;

    @Column(name = "valoracion")
    private Integer valoracion;

    @Column(name = "codigo_reserva")
    private String codigoReserva;

    @Column(name = "activo")
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "id_ocio_nocturno")
    private OcioNocturno ocio;

}
