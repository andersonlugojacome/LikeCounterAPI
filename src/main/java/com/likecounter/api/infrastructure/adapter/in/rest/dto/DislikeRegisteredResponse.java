package com.likecounter.api.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta al registrar un nuevo dislike")
public record DislikeRegisteredResponse(
        @Schema(description = "Mensaje descriptivo de la operación", example = "Dislike registrado correctamente")
        String message,
        @Schema(description = "Cantidad total después de registrar el dislike", example = "6")
        long count
) {
}
