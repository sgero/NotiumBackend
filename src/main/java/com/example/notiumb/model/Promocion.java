package com.example.notiumb.model;


import com.example.notiumb.model.enums.TipoPromocion;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "promocion", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {""})
@Builder
public class Promocion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tipo")
    @Enumerated(EnumType.ORDINAL)
    private TipoPromocion tipo;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "foto")
    private String foto;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "activo")
    private Boolean activo = true;

    @OneToMany(mappedBy = "promocion", fetch = FetchType.LAZY)
    private Set<EntradaOcioCliente> entradaOcioClienteSet;
    @OneToMany(mappedBy = "promocion", fetch = FetchType.LAZY)
    private Set<ReservadoOcioCliente> reservadoOcioClientes;

    @OneToMany(mappedBy = "promocion", fetch = FetchType.LAZY)
    private Set<ListaOcioCliente> listaOcioClientes;

    //    private Restaurante restaurante;
}
