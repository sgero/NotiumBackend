package com.example.notiumb.utilidades;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Mensajes {
    protected Mensajes() {
    }

    private static final String FICHERO_ERRORES = "error.messages_error";
    private static final String FICHERO_MENSAJES = "mensaje.messages";
    private static final String FICHERO_ENUMS = "enum.messages_enum";
    private static final String FICHERO_BBDD = "bbdd.messages_bbdd";

    private static final String FICHERO_ERROR_REP = "representaciones.error.messages_error";
    private static final String FICHERO_MSG_REP = "representaciones.mensaje.messages";
    private static final String FICHERO_ENUMS_REP = "representaciones.enum.messages_enum";

    private static final String FICHERO_ERROR_BOU = "bou.error.messages_error";
    private static final String FICHERO_MSG_BOU = "bou.mensaje.messages";

    public static String getMessageErrorRep(String key, Locale idioma) {
        return getMessageErrorRep(key, null, idioma);
    }

    public static String getMessageErrorRep(String key, Object[] params, Locale idioma) {
        return getMessageResourceString(FICHERO_ERROR_REP, key, (Object[]) params, idioma);
    }

    public static String getMessageRep(String key, Locale idioma) {
        return getMessageResourceString(FICHERO_MSG_REP, key, (Object[]) null, idioma);
    }

    public static String getMessageRep(String key, Object[] params, Locale idioma) {
        return getMessageResourceString(FICHERO_MSG_REP, key, params, idioma);
    }

    public static String getMessageENUMRep(String messageKey, Locale idioma) {
        return getMessageResourceString(FICHERO_ENUMS_REP, messageKey, null, idioma);
    }

    public static String getMessageErrorBou(String key, Locale idioma) {
        return getMessageErrorBou(key, null, idioma);
    }

    public static String getMessageErrorBou(String key, Object[] params, Locale idioma) {
        return getMessageResourceString(FICHERO_ERROR_BOU, key, (Object[]) params, idioma);
    }

    public static String getMessageBou(String key, Locale idioma) {
        return getMessageResourceString(FICHERO_MSG_BOU, key, (Object[]) null, idioma);
    }

    public static String getMessageBou(String key, Object[] params, Locale idioma) {
        return getMessageResourceString(FICHERO_MSG_BOU, key, params, idioma);
    }
    public static String getMessageResourceString(String bundleName, String key, Object[] params,
                                                  Locale locale) {

        String text;
        if (locale == null) {
            text = getMessageResourceStringDefault(bundleName, key, params);
        } else {

            ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);

            try {
                text = bundle.getString(key);
            } catch (MissingResourceException e) {
                text = getMessageResourceStringDefault(bundleName, key, params);
            }
        }
        if (params != null) {
            MessageFormat mf = new MessageFormat(text, locale);
            text = mf.format(params, new StringBuffer(), null).toString();
        }

        return text;
    }

    private static String getMessageResourceStringDefault(String bundleName, String key,
                                                          Object[] params) {
        String text;
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName, new Locale("es", "ES"));

        try {
            text = bundle.getString(key);
        } catch (MissingResourceException e) {
            text = "?? key " + key + " not found ??";
        }

        if (params != null) {
            MessageFormat mf = new MessageFormat(text, new Locale("es", "ES"));
            text = mf.format(params, new StringBuffer(), null).toString();
        }

        return text;
    }

    public static String getMessage(String key, Locale idioma) {
        return getMessageResourceString(FICHERO_MENSAJES, key, null, idioma);
    }

    public static String getMessage(String key, Object[] params, Locale idioma) {
        return getMessageResourceString(FICHERO_MENSAJES, key, params, idioma);
    }

    public static String getMessageError(String key, Locale idioma) {
        return getMessageResourceString(FICHERO_ERRORES, key, null, idioma);
    }

    public static String getMessageError(String key, Object[] params, Locale idioma) {
        return getMessageResourceString(FICHERO_ERRORES, key, params, idioma);
    }
    public static String getMessageENUM(String key, Locale idioma) {
        return getMessageResourceString(FICHERO_ENUMS, key, null, idioma);
    }

    public static String getMessageENUM(String key, Object[] params, Locale idioma) {
        return getMessageResourceString(FICHERO_ENUMS, key, params, idioma);
    }

    public static String getMessageBBDD(String key, Locale idioma) {
        return getMessageResourceString(FICHERO_BBDD, key, null, idioma);
    }

    public static String getMessageBBDD(String key, Object[] params, Locale idioma) {
        return getMessageResourceString(FICHERO_BBDD, key, params, idioma);
    }
}
