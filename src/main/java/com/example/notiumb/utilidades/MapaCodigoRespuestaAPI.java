package com.example.notiumb.utilidades;

public class MapaCodigoRespuestaAPI {
    private MapaCodigoRespuestaAPI(){}
    public static final CodigoRespuestaAPI CODIGO_200 = new CodigoRespuestaAPI("200", "ok");
    public static final CodigoRespuestaAPI CODIGO_200_EVENTO_CREADO = new CodigoRespuestaAPI("200", "evento creado correctamente");
    public static final CodigoRespuestaAPI CODIGO_ERROR_500 = new CodigoRespuestaAPI("500", "error.servidor");
    public static final CodigoRespuestaAPI CODIGO_ERROR_1 = new CodigoRespuestaAPI("1", "error.dto.null");
    public static final CodigoRespuestaAPI CODIGO_ERROR_2 = new CodigoRespuestaAPI("2", "error.mas entradas vendidas que aforo");

}
