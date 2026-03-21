package com.likecounter.api.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta con el total acumulado de dislikes")
public record DislikeCountResponse(
        @Schema(description = "Cantidad total actual de dislikes", example = "5")
        long count
) {
}
