package com.example.notiumb.model;

import com.example.notiumb.model.enums.Consumiciones;
import com.example.notiumb.model.enums.Rol;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "entrada_ocio", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"evento"})
public class EntradaOcio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "total_entradas", nullable = false)
    private Integer totalEntradas;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @Column(name = "detalle_entrada", nullable = false)
    private String detalleEntrada ;

    @Column(name = "consumiciones", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Consumiciones consumiciones;

    @OneToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;

    @OneToMany(mappedBy = "entradaOcio", fetch = FetchType.LAZY)
    private Set<EntradaOcioCliente> entradaOcioClienteSet;

}
