package com.example.notiumb.model;

import com.example.notiumb.model.enums.CodigoVestimentaOcio;
import com.example.notiumb.model.enums.EdadMinimaOcio;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "evento", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"ocioNocturno", "entradaOcio", "listasOcio", "reservadoOcio"})
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

    @Column(name = "cartel")
    private String cartel;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "id_ocio_nocturno", nullable = false)
    private OcioNocturno ocioNocturno;

    @OneToOne(mappedBy = "evento", fetch = FetchType.LAZY)
    private EntradaOcio entradaOcio;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento", fetch = FetchType.LAZY)
    private Set<ListaOcio> listasOcio;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "evento", fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservado_ocio", nullable = false)
    private ReservadoOcio reservadoOcio;

    @OneToMany(mappedBy = "chatEvento", fetch = FetchType.LAZY)
    private Set<ChatMensaje> chatMensajeSet;
}
