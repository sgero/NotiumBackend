package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mesa_restaurante", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {""})
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "num_plazas" , nullable = false)
    private Integer num_plazas;

    @Column(name = "reservada", nullable = false)
    private Boolean reservada;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;


}
