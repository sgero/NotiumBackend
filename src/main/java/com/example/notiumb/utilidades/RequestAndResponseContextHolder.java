package com.example.notiumb.utilidades;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestAndResponseContextHolder {
    public static final String RESPONSE_NAME_AT_ATTRIBUTES = ServletRequestAttributes.class
            .getName()
            + ".ATTRIBUTE_NAME";

    public static HttpServletResponse response() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = null;
        if (requestAttributes != null) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            response = (HttpServletResponse) servletRequestAttributes.getAttribute(
                    RESPONSE_NAME_AT_ATTRIBUTES, RequestAttributes.SCOPE_REQUEST);
        }
        return response;
    }

    public static HttpServletRequest request() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (requestAttributes != null) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            request = servletRequestAttributes.getRequest();
        }
        return request;
    }

    public static void response(HttpServletResponse response) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if (requestAttributes != null) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            servletRequestAttributes.setAttribute(RESPONSE_NAME_AT_ATTRIBUTES, response,
                    RequestAttributes.SCOPE_REQUEST);
        }
    }
}
