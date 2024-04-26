package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "formato", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "productoFormatoSet")
public class Formato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "formato", nullable = false)
    private String formato;

    @OneToMany(mappedBy = "formato", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductoFormato> productoFormatoSet;
}
