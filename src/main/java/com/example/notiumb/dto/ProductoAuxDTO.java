package com.example.notiumb.dto;

import com.example.notiumb.model.enums.TipoCategoria;
import lombok.Data;

@Data
public class ProductoAuxDTO {

    private String nombre;

    private String tipoCategoria;

    private String username;
}
