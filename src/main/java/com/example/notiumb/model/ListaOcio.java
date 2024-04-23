package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="lista_ocio", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"rpp", "evento"})
public class ListaOcio {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "precio", nullable = false)
    private Double precio;
    @Column(name = "total_invitaciones", nullable = false)
    private Double total_invitaciones;
    @Column(name = "activo", nullable = false)
    private Boolean activo = true;
    @ManyToOne
    @JoinColumn(name = "id_rpp", nullable = false)
    private Rpp rpp;
    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listaOcio", fetch = FetchType.LAZY)
    private Set<ListaOcioCliente> listasOcioCliente;

}
