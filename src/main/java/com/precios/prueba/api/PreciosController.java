package com.precios.prueba.api;

import com.precios.prueba.dominio.dtos.PriceDTO;
import com.precios.prueba.dominio.request.PriceRequest;
import com.precios.prueba.infraestructura.adapters.primary.PriceImpl;
import com.precios.prueba.utils.PlantillaResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/precios")
@Tag(name = "Precios" , description = "Endpoints relacionados con el manejo precios")
@RequiredArgsConstructor
public class PreciosController {
    private final PriceImpl priceImpl;

    @PostMapping(value = "/consultar")
    public @ResponseBody ResponseEntity<PlantillaResponse<PriceDTO> > getPrice(@RequestBody PriceRequest priceRequest) {
        PlantillaResponse<PriceDTO> response =priceImpl.getPrice(priceRequest);
        return new ResponseEntity<>(response,response.getHttpStatus());
    }
}
