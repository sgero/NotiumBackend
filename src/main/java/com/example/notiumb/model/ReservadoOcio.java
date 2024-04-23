package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="reservado_ocio", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"evento"})
public class ReservadoOcio {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "reservados_disponibles", nullable = false)
    private Integer reservadosDisponibles;
    @Column(name = "personas_max_por_reservado", nullable = false)
    private Integer personasMaximasPorReservado;
    @Column(name = "activo", nullable = false)
    private Boolean activo = true;
    @Column(name = "precio", nullable = false)
    private Double precio;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservadoOcio", fetch = FetchType.LAZY)
    private Set<ReservadoOcioCliente> reservadosOcioCliente = new HashSet<>(0);


}
