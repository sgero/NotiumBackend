package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "turno_restaurante", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"restaurante","reservasSet"})
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "hora_inicio" , nullable = false)
    private Time hora_inicio;

    @Column(name = "hora_fin" , nullable = false)
    private Time hora_fin;

    @Column(name = "activo" , nullable = false)
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_restaurante" , nullable = false)
    private Restaurante restaurante;

    @OneToMany(mappedBy = "turno", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reserva> reservasSet;




}
