package com.precios.prueba.utils.enums;



public enum MensajesRespuesta {
    CREADO("Creado(a) correctamente"),
    NOT_FOUND("No se encontraron datos"),
    GET("Se obtuvieron datos correctamente"),
    ACTUALIZADO("Actualizado correctamente"),
    ELIMINADO("Eliminado correctamente"),
    EMAIL_NO_ENCONTRADO("Email o contraseña inválida"),
    FALLO("Algo salió mal"),

    USER_ISFOUND("El Usuario ya se encuentra registrado"),
    USER_lOGEADO("Datos Correctos");

    private final String mensaje;

    MensajesRespuesta(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}



























