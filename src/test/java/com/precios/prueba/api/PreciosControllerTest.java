package com.precios.prueba.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.precios.prueba.dominio.dtos.PriceDTO;
import com.precios.prueba.dominio.request.PriceRequest;
import com.precios.prueba.infraestructura.adapters.primary.PriceImpl;
import com.precios.prueba.utils.BuildResponses;
import com.precios.prueba.utils.PlantillaResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = PreciosController.class)
public class PreciosControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceImpl priceImpl;

    @MockBean
    private BuildResponses<PriceDTO> buildResponses;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
    void getPrice_Test() throws Exception {
        PriceRequest[] requests = {
                new PriceRequest(LocalDateTime.of(2020, 6, 14, 10, 0), 35455, 1),
                new PriceRequest(LocalDateTime.of(2020, 6, 14, 16, 0), 35455, 1),
                new PriceRequest(LocalDateTime.of(2020, 6, 14, 21, 0), 35455, 1),
                new PriceRequest(LocalDateTime.of(2020, 6, 15, 10, 0), 35455, 1),
                new PriceRequest(LocalDateTime.of(2020, 6, 16, 21, 0), 35455, 1)
        };


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime appDate = LocalDateTime.parse("2020-06-14 10:00:00",formatter);

        PriceDTO priceDTO = PriceDTO.builder()
                .productId(35455)
                .brandId(1)
                .price(BigDecimal.valueOf(1))
                .appDate(appDate)
                .finalPrice(BigDecimal.valueOf(35.5))
                .build();


        PlantillaResponse<PriceDTO> response = PlantillaResponse.<PriceDTO>builder()
                .message("Se obtuvieron datos correctamente")
                .httpStatus(HttpStatus.OK)
                .dataList(Collections.singletonList(
                        priceDTO
                ))
                .build();


        when(priceImpl.getPrice(any(PriceRequest.class))).thenReturn(response);

        for (PriceRequest request : requests) {
            mockMvc.perform(post("/precios/consultar")  // Agrega el prefijo del servidor
                            .content(objectMapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.dataList[0].brandId").value(priceDTO.getBrandId()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.dataList[0].productId").value(priceDTO.getProductId()))

                    .andExpect(MockMvcResultMatchers.jsonPath("$.dataList[0].finalPrice").value(priceDTO.getFinalPrice()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.dataList[0].price").value(priceDTO.getPrice()));
        }








    }


}
