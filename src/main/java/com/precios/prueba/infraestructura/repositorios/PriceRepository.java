package com.precios.prueba.infraestructura.repositorios;


import com.precios.prueba.infraestructura.entity.Price;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface PriceRepository extends ReactiveCrudRepository<Price, Integer> {

    @Query(value = "SELECT p.* FROM PRICES p JOIN BRANDS b ON p.BRAND_ID = b.BRAND_ID WHERE b.BRAND_ID = :brandId AND p.PRODUCT_ID = :productId AND (:appDate BETWEEN p.START_DATE AND p.END_DATE) ORDER BY p.PRIORITY ASC")

    Flux<Price> findByBrandProductAndDate(Integer brandId, Integer productId, LocalDateTime appDate);

}
