package com.precios.prueba.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/precios")
@Tag(name = "Precios" , description = "Endpoints relacionados con el manejo precios")
public class PreciosController {
}
