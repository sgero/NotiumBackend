package com.example.notiumb.model;

import com.example.notiumb.model.enums.Consumiciones;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="lista_ocio", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"rpp", "evento", "listasOcioCliente"})
public class ListaOcio {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "total_invitaciones", nullable = false)
    private Integer total_invitaciones;

    @Column(name = "activo", nullable = false)
    private Boolean activo = false;

    @Column(name = "detalle_lista", nullable = false)
    private String detalleLista ;

    @Column(name = "consumiciones", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Consumiciones consumiciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rpp", nullable = false)
    private Rpp rpp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listaOcio")
    private Set<ListaOcioCliente> listasOcioCliente;

}
