package com.example.notiumb.utilidades;

import java.util.logging.Logger;

public class ULogger {
    protected ULogger(){}
    protected static final org.apache.logging.log4j.Logger LOG = (org.apache.logging.log4j.Logger) Logger.getLogger(String.valueOf(Logger.class));
    public static void error(Throwable t){
        LOG.error("", t);
    }
}
