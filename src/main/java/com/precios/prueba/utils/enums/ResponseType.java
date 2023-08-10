package com.precios.prueba.utils.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum ResponseType {
    GET(1, MensajesRespuesta.GET.getMensaje(), HttpStatus.OK),
    FALLO(2, MensajesRespuesta.FALLO.getMensaje(), HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND(3, MensajesRespuesta.NOT_FOUND.getMensaje(),HttpStatus.NOT_FOUND);

    private final int code;

    private final String message;

    private final HttpStatus httpStatus;


    public static ResponseType fromCode(int code) {
        for (ResponseType responseType : ResponseType.values()) {
            if (responseType.code == code) {
                return responseType;
            }
        }
        return null;
    }
}
