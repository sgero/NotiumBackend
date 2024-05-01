package com.example.notiumb.utilidades;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel(value = "RespuestaDTO", description = "Objeto genérico para transmitir la información de la respuesta de un servicio web")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespuestaDTO {
    @ApiModelProperty(value = "Código para informar sobre el estado de la respuesta", position = 1, required = true)
    private String codigo;

    @ApiModelProperty(value = "Descripción del código de la respuesta", position = 2, required = true)
    private String mensaje;

    @ApiModelProperty(value = "Objeto de respuesta", position = 3, required = true)
    private Object object;

    public RespuestaDTO(){super();}

}
