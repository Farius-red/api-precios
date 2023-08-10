package com.precios.prueba.infraestructura.adapters.primary;


import com.precios.prueba.dominio.dtos.PriceDTO;
import com.precios.prueba.dominio.mappers.PriceDTOMapper;
import com.precios.prueba.dominio.request.PriceRequest;
import com.precios.prueba.infraestructura.repositorios.PriceRepository;
import com.precios.prueba.infraestructura.servicios.PriceService;
import com.precios.prueba.utils.BuildResponses;
import com.precios.prueba.utils.PlantillaResponse;
import com.precios.prueba.utils.enums.ResponseType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
@RequiredArgsConstructor
public class PriceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final PriceDTOMapper priceDTOMapper;
    private final BuildResponses<PriceDTO> buildResponses;

    @Override
    public PlantillaResponse<PriceDTO> getPrice(PriceRequest priceRequest) {
        Flux<PriceDTO> priceDtoFlux = priceRepository.findByBrandProductAndDate(priceRequest.getBrandId(), priceRequest.getProductId(), priceRequest.getAppDate())
                .map(price ->  priceDTOMapper.priceToPriceDTO(price,priceRequest.getAppDate()));
                return buildResponses.buildResponse(ResponseType.GET.getCode(), priceDtoFlux);
    }


}
