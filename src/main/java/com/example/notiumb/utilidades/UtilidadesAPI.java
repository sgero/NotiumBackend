package com.example.notiumb.utilidades;

public class UtilidadesAPI {
    public static final int OK = 200;


    protected UtilidadesAPI() {
    }

    public static void setearMensaje(RespuestaDTO respuesta, CodigoRespuestaAPI codigo,
                                     Object[] params, Object objeto) {

        respuesta.setCodigo(codigo.getCodigo().getKey());
        respuesta.setMensaje(codigo.getCodigo().getValue());
        respuesta.setObject(objeto);
    }

    public static void setearMensaje(RespuestaDTO respuesta, CodigoRespuestaAPI codigo,
                                     Object objeto) {
        setearMensaje(respuesta, codigo, null, objeto);
    }
    public static void setearMensajeControladoras(RespuestaDTO respuesta, String codigo,
                                                  Object objeto) {
        respuesta.setCodigo(codigo);
        respuesta.setObject(objeto.toString());
    }

    public static void setearMensaje(RespuestaDTO respuesta, CodigoRespuestaAPI codigo) {
        setearMensaje(respuesta, codigo, null, null);
    }

}
