package com.example.notiumb.model;

import com.example.notiumb.model.enums.Rol;
import com.example.notiumb.model.enums.TipoBebida;
import com.example.notiumb.model.enums.TipoPlatoRestaurante;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "producto_tipo_plato", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"producto"})
public class ProductoTipoPlato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @OneToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @Column(name = "tipo_bebida", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TipoPlatoRestaurante tipoPlatoRestaurante;


}
