package com.example.notiumb.utilidades;

public class MapaCodigoRespuestaAPI {
    private MapaCodigoRespuestaAPI(){}
    public static final CodigoRespuestaAPI CODIGO_200 = new CodigoRespuestaAPI("200", "ok");
    public static final CodigoRespuestaAPI CODIGO_200_EVENTO_UNICO_CREADO = new CodigoRespuestaAPI("200", "evento único creado correctamente");
    public static final CodigoRespuestaAPI CODIGO_200_EVENTO_EDITADO = new CodigoRespuestaAPI("200", "evento editado correctamente");
    public static final CodigoRespuestaAPI CODIGO_200_EVENTO_LISTAR = new CodigoRespuestaAPI("200", "eventos listados correctamente");
    public static final CodigoRespuestaAPI CODIGO_ERROR_500 = new CodigoRespuestaAPI("500", "error.servidor");
    public static final CodigoRespuestaAPI CODIGO_ERROR_1 = new CodigoRespuestaAPI("1", "error.dto.null");
    public static final CodigoRespuestaAPI CODIGO_ERROR_2 = new CodigoRespuestaAPI("2", "error.mas entradas vendidas que aforo");
    public static final CodigoRespuestaAPI CODIGO_200_EVENTO_ELIMINADO = new CodigoRespuestaAPI("2", "evento eliminado");


}
