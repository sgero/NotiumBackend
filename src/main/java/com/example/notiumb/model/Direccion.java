package com.example.notiumb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "direccion", schema = "notium", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "calle", nullable = false)
    private String calle;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "puerta")
    private String puerta;

    @Column(name = "codigo_postal", nullable = false)
    private Integer codigoPostal;

    @Column(name = "ciudad", nullable = false)
    private String ciudad;

    @Column(name = "provincia", nullable = false)
    private String provincia;

    @Column(name = "pais", nullable = false)
    private String pais;

    @OneToOne(mappedBy = "direccion", fetch = FetchType.LAZY)
    private Rpp rpp;

    @OneToOne(mappedBy = "direccion", fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToOne(mappedBy = "direccion", fetch = FetchType.LAZY)
    private OcioNocturno ocioNocturno;

    @OneToOne(mappedBy = "direccion", fetch = FetchType.LAZY)
    private Restaurante restaurante;

}
