package com.example.notiumb.model;

import com.example.notiumb.model.enums.TipoCategoria;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producto", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {""})
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "precio", nullable = false)
    private Double precio;
    @Column(name = "tipo_categoria", nullable = false)
    private TipoCategoria tipoCategoria;
    @Column(name = "activo", nullable = false)
    private Boolean activo;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_carta_ocio", nullable = false)
    private CartaOcio carta_ocio;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_carta_rest", nullable = false)
    private CartaRestaurante carta_res;

}
