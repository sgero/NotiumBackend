package com.example.notiumb.model;


import com.example.notiumb.model.enums.TipoPromocion;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="promocion")
public class Promocion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private TipoPromocion tipo;

    private String titulo;

    private String foto;

    private Boolean activo;

    @OneToMany(mappedBy = "promocion", fetch = FetchType.LAZY)
    private Set<EntradaOcioCliente> entradaOcioClienteSet;
    @OneToMany(mappedBy = "promocion", fetch = FetchType.LAZY)
    private Set<ReservadoOcioCliente> reservadoOcioClientes;

    @OneToMany(mappedBy = "promocion", fetch = FetchType.LAZY)
    private Set<ListaOcioCliente> listaOcioClientes;

    //    private Restaurante restaurante;
}
