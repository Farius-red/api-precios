package com.precios.prueba.utils;

import static org.junit.jupiter.api.Assertions.*;

import com.precios.prueba.utils.enums.ResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class BuildResponsesTest {

    @Mock
    private Publisher<Object> mockPublisher;

    private BuildResponses<Object> buildResponses;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        buildResponses = new BuildResponses<>();
    }

    @Test
    void buildResponseWithFlux() {
        Flux<Object> flux = Flux.empty();

        PlantillaResponse<Object> response = buildResponses.buildResponse(ResponseType.GET.getCode(), flux);

        assertEquals(ResponseType.NOT_FOUND.getMessage(), response.getMessage());
        assertEquals(ResponseType.GET.getHttpStatus(), response.getHttpStatus());
    }

    @Test
    void buildResponseWithMono() {
        Mono<Object> mono = Mono.empty();

        PlantillaResponse<Object> response = buildResponses.buildResponse(ResponseType.GET.getCode(), mono);

        assertEquals(ResponseType.GET.getMessage(), response.getMessage());
        assertEquals(ResponseType.GET.getHttpStatus(), response.getHttpStatus());

    }

    @Test
    void buildResponseWithInvalidType() {
        assertThrows(IllegalArgumentException.class,
                () -> buildResponses.buildResponse(ResponseType.GET.getCode(), mockPublisher));
    }

    @Test
    void buildResponseWithInvalidResponseType() {
        assertThrows(IllegalArgumentException.class,
                () -> buildResponses.buildResponse(999, Flux.empty()));
    }
}
