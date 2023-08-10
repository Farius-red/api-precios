package com.precios.prueba.infraestructura.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table("PRICES")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    @Id
    @Column("PRICE_ID")
    private Integer id;

    @Column("BRAND_ID")
    private Integer brandId;

    @Column("START_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")    private LocalDateTime startDate;

    @Column("END_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    @Column("PRICE_LIST")
    private BigDecimal priceList;

    @Column("PRODUCT_ID")
    private Integer productId;

    @Column("PRIORITY")
    private Integer priority;

    @Column("PRICE")
    private BigDecimal finalPrice;

    @Column("CURR")
    private String curr;
}
