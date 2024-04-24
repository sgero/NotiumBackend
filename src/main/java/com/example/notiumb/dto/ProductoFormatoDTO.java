package com.example.notiumb.dto;

import com.example.notiumb.model.Formato;
import com.example.notiumb.model.Producto;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.Data;

@Data
public class ProductoFormatoDTO {
    private Integer id;

    @DecimalMin("0.19") @Digits(integer = 6, fraction = 2)
    private Double precio;

    @Valid
    private ProductoDTO productoDTO;

    @Valid
    private FormatoDTO formatoDTO;
}
