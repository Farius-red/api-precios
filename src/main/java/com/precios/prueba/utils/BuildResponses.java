package com.precios.prueba.utils;


import com.precios.prueba.utils.enums.ResponseType;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class BuildResponses<E> {

    /**
     * Construye una respuesta genérica de PlantillaResponse a partir de los datos proporcionados.
     *
     * @param tipoRespuesta El código del tipo de respuesta, debe corresponder a un valor válido en ResponseType.
     * @param e             Un objeto Publisher que contiene los datos a incluir en la respuesta.
     *                      Puede ser un Flux o un Mono que contenga los datos.
     *                       El tipo de datos contenidos en el Publisher.
     * @return Un objeto PlantillaResponse que contiene los datos proporcionados y el tipo de respuesta especificado.
     * @throws IllegalArgumentException Si el tipo de respuesta no es válido o el tipo de datos no es compatible con Flux o Mono.
     */

    public PlantillaResponse<E> buildResponse(int tipoRespuesta, Publisher<E> e) {
        ResponseType responseType = ResponseType.fromCode(tipoRespuesta);
        if (responseType != null) {
            PlantillaResponse.PlantillaResponseBuilder<E> responseBuilder = PlantillaResponse.<E>builder()
                    .message(responseType.getMessage())
                    .httpStatus(responseType.getHttpStatus());

            if (e != null) {
                if (e instanceof Flux<E> flux) {
                    List<E> dataList = flux.collectList().block();
                    if (!dataList.isEmpty()) responseBuilder.dataList(dataList);
                    else responseBuilder.message(ResponseType.NOT_FOUND.getMessage());
                } else if (e instanceof Mono) {
                    Mono<E> mono = (Mono<E>) e;
                    E data = mono.block();
                    responseBuilder.data(data);
                } else {
                    throw new IllegalArgumentException("Tipo de dato no válido para el argumento 'e'");
                }
            }

            return responseBuilder.build();
        }

        throw new IllegalArgumentException("Tipo de respuesta no válido: " + tipoRespuesta);
    }
}
