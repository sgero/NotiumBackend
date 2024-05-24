package com.example.notiumb.utilidades;

public class MapaCodigoRespuestaAPI {
    private MapaCodigoRespuestaAPI(){}
    public static final CodigoRespuestaAPI CODIGO_200 = new CodigoRespuestaAPI("200", "ok");
    public static final CodigoRespuestaAPI CODIGO_200_EVENTO_UNICO_CREADO = new CodigoRespuestaAPI("200", "evento único creado correctamente");
    public static final CodigoRespuestaAPI CODIGO_200_EVENTO_EDITADO = new CodigoRespuestaAPI("200", "evento editado correctamente");
    public static final CodigoRespuestaAPI CODIGO_200_EVENTO_LISTAR = new CodigoRespuestaAPI("200", "eventos listados correctamente");
    public static final CodigoRespuestaAPI CODIGO_200_ENTRADAS_COMPRADAS = new CodigoRespuestaAPI("200", "entradas.compradas");
    public static final CodigoRespuestaAPI CODIGO_ERROR_500 = new CodigoRespuestaAPI("500", "error.servidor");
    public static final CodigoRespuestaAPI CODIGO_ERROR_1 = new CodigoRespuestaAPI("1", "error.dto.null");
    public static final CodigoRespuestaAPI CODIGO_ERROR_2 = new CodigoRespuestaAPI("2", "error.mas entradas vendidas que aforo");
    public static final CodigoRespuestaAPI CODIGO_200_EVENTO_ELIMINADO = new CodigoRespuestaAPI("2", "evento eliminado");
    public static final CodigoRespuestaAPI CODIGO_400_MESA_NO_CREADA = new CodigoRespuestaAPI("400", "La mesa no se ha podido crear");
    public static final CodigoRespuestaAPI CODIGO_200_MESA_CREADA = new CodigoRespuestaAPI("200", "La mesa se ha creado");
    public static final CodigoRespuestaAPI CODIGO_200_MESA_DESACTIVADA = new CodigoRespuestaAPI("200", "La mesa se ha desactivado");
    public static final CodigoRespuestaAPI CODIGO_400_NO_DESACTIVADA = new CodigoRespuestaAPI("400", "La mesa no se ha desactivado");
    public static final CodigoRespuestaAPI CODIGO_200_TURNO_CREADO = new CodigoRespuestaAPI("200", "El turno se ha creado");
    public static final CodigoRespuestaAPI CODIGO_400_TURNO_NO_CREADO = new CodigoRespuestaAPI("400", "El turno no se ha creado");
    public static final CodigoRespuestaAPI CODIGO_200_TURNO_DESACTIVADO = new CodigoRespuestaAPI("200", "El turno se ha desactivado");
    public static final CodigoRespuestaAPI CODIGO_400_TURNO_NO_DESACTIVADO = new CodigoRespuestaAPI("400", "El turno no se ha desactivado");
    public static final CodigoRespuestaAPI CODIGO_200_VALORACION_CREADA = new CodigoRespuestaAPI("200", "La valoracion se ha creado correctamente");
    public static final CodigoRespuestaAPI CODIGO_400_VALORACION_NO_CREADA = new CodigoRespuestaAPI("400", "La valoracion no se ha creado correctamente");
    public static final CodigoRespuestaAPI CODIGO_200_RANKING_ACTUALIZADO = new CodigoRespuestaAPI("200", "El ranking ha sido actualizado correctamente");
    public static final CodigoRespuestaAPI CODIGO_400_RANKING_NO_ACTUALIZADO = new CodigoRespuestaAPI("400", "El ranking no ha sido actualizado correctamente");

    public static final CodigoRespuestaAPI CODIGO_ERROR_EVENTO = new CodigoRespuestaAPI("412", "error.evento.null");
    public static final CodigoRespuestaAPI CODIGO_ERROR_AFORO = new CodigoRespuestaAPI("412", "error.aforo.completo");
    public static final CodigoRespuestaAPI CODIGO_ERROR_DATOS_CLIENTE = new CodigoRespuestaAPI("412", "error: los datos del primer asistente no son iguales a los del cliente que realiza la compra");
    public static final CodigoRespuestaAPI CODIGO_ERROR_DATOS = new CodigoRespuestaAPI("412", "error.datos.incorrectos");

}
