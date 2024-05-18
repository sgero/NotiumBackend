package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name="lista_ocio_cliente", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"cliente", "listaOcio", "datosComprador"})
@Builder
public class ListaOcioCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_lista_ocio", nullable = false)
    private ListaOcio listaOcio;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_promocion")
    private Promocion promocion;

    @OneToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    @JoinColumn(name = "datos_comprador", nullable = false)
    private DatosComprador datosComprador;
}
