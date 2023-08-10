package com.precios.prueba.dominio.mappers;

import com.precios.prueba.dominio.dtos.PriceDTO;
import com.precios.prueba.infraestructura.entity.Price;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PriceDTOMapper {

  public   PriceDTO priceToPriceDTO(Price price, LocalDateTime dateTime){
        return PriceDTO.builder()
                .brandId(price.getBrandId())
                .price(price.getPriceList())
                .finalPrice(price.getFinalPrice())
                .appDate(dateTime)
                .build();
  }
}
