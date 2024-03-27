package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.*;

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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_evento")
    private Evento evento;

}