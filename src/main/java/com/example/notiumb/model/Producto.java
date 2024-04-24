package com.example.notiumb.model;

import com.example.notiumb.model.enums.TipoCategoria;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "producto", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"carta_ocio","carta_res"})
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "tipo_categoria", nullable = false)
    private TipoCategoria tipoCategoria;
    @Column(name = "activo", nullable = false)
    private Boolean activo;
    @ManyToOne
    @JoinColumn(name = "id_carta_ocio")
    private CartaOcio carta_ocio;
    @ManyToOne
    @JoinColumn(name = "id_carta_rest")
    private CartaRestaurante carta_res;

    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductoFormato> productoFormatoSet;

}
