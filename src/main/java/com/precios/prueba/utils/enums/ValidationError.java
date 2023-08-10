package com.precios.prueba.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidationError {
    NULL_FIELD("El campo no puede ser nulo."),
    INVALID_TYPE("Tipo de dato inválido."),
    MINIMUM_LENGTH("El campo debe tener al menos {0} caracteres."),
    MAXIMUM_LENGTH("El campo debe tener como máximo {0} caracteres.");
    // Agrega aquí más tipos de errores de validación según tus necesidades
    private final String message;

    }
