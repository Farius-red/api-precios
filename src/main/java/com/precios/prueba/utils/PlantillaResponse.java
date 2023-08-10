package com.precios.prueba.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlantillaResponse<E> {
    private String message;
    private HttpStatus httpStatus;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<E> dataList;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private E data;


}
