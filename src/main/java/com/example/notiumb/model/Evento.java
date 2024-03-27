package com.example.notiumb.model;

import com.example.notiumb.model.enums.CodigoVestimentaOcio;
import com.example.notiumb.model.enums.EdadMinimaOcio;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "evento", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;

    @Column(name = "tematica", nullable = false)
    private String tematica;

    @Column(name = "codigo_vestimenta", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private CodigoVestimentaOcio codigoVestimentaOcio;

    @Column(name = "edad", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private EdadMinimaOcio edadMinimaOcio;

    @Column(name = "aforo", nullable = false)
    private Integer aforo;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "id_ocio_nocturno", nullable = false)
    private OcioNocturno ocioNocturno;

}
