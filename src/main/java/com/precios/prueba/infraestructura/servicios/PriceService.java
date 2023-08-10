package com.precios.prueba.infraestructura.servicios;

import com.precios.prueba.dominio.dtos.PriceDTO;
import com.precios.prueba.dominio.request.PriceRequest;
import com.precios.prueba.utils.PlantillaResponse;

public interface PriceService {
    PlantillaResponse<PriceDTO> getPrice(PriceRequest priceRequest);
}
