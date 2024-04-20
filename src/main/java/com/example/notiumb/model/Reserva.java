package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reserva_restaurante", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "codigo_reserva", nullable = false)
    private String codigo_reserva;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_turno_restaurante", nullable = false)
    private Turno turno;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "id_mesa_restaurante", nullable = false)
    private Mesa mesa;
}
