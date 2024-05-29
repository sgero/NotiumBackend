package com.example.notiumb.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListadoProductosDTO {

    private ProductoDTO producto;
    private List<ProductoFormatoDTO> formatos;
}
