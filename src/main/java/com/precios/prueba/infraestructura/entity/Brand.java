package com.precios.prueba.infraestructura.entity;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("BRANDS")
public class Brand {

    @Id
    @Column("BRAND_ID")
    private Integer id;

    @Column("NAME")
    private String name;
}
