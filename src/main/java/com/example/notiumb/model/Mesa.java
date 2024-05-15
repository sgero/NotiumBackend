package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mesa_restaurante", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"restaurante","reservas"})
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "num_plazas" , nullable = false)
    private Integer numPlazas;

    @Column(name = "reservada", nullable = false)
    private Boolean reservada;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "mesa", fetch = FetchType.LAZY)
    private Set<Reserva> reservas = new HashSet<>(0);
}
