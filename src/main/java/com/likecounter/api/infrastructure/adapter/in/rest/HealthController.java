package com.likecounter.api.infrastructure.adapter.in.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Tag(name = "Health", description = "Endpoints básicos de verificación")
public class HealthController {

    @GetMapping("/hello")
    @Operation(
            summary = "Hola Mundo",
            description = "Endpoint simple para comprobar que la API está respondiendo."
    )
    public Map<String, String> hello() {
        return Map.of("message", "LikeCounterAPI is running");
    }
}
