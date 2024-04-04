package com.example.notiumb.dto;

import com.example.notiumb.model.Producto;
import com.example.notiumb.model.enums.TipoBebida;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductoTipoBebidaDTO {


    private Integer id;

    @DecimalMin("0.20") @Digits(integer = 6, fraction = 2)
    private Double precio;

    @Positive
    private Integer idProducto;

    @Valid
    private TipoBebida tipoBebida;
}
