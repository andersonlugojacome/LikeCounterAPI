package com.likecounter.api.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta al registrar un nuevo like")
public record LikeRegisteredResponse(
        @Schema(description = "Mensaje descriptivo de la operación", example = "Like registrado correctamente")
        String message,
        @Schema(description = "Cantidad total después de registrar el like", example = "6")
        long count
) {
}
