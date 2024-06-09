package com.example.notiumb.model;

import com.example.notiumb.model.enums.DiasARepetirCicloEventoOcio;
import com.example.notiumb.model.enums.EdadMinimaOcio;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "turno_dias_semana", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnosDiasSemana {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "dia_semana", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DiasARepetirCicloEventoOcio dias;

    @ManyToOne
    @JoinColumn(name = "id_turno_restaurante", nullable = false)
    private Turno turno;

}
