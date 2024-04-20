package com.example.notiumb.utilidades;

import java.util.AbstractMap;
import java.util.Map;

public class CodigoRespuestaAPI {
    private Map.Entry<String, String> codigo;
    public CodigoRespuestaAPI(){}

    public CodigoRespuestaAPI(String codigo, String keyProperties){
        setCodigo(new AbstractMap.SimpleEntry<String, String>(codigo, keyProperties));
    }
    public Map.Entry<String, String> getCodigo(){
        return codigo;
    }
    public void setCodigo(Map.Entry<String, String> codigo){
        this.codigo = codigo;
    }

}

