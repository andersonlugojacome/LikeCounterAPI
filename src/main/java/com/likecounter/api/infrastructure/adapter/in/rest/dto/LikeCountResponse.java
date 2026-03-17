package com.likecounter.api.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta con el total acumulado de likes")
public record LikeCountResponse(
        @Schema(description = "Cantidad total actual de likes", example = "5")
        long count
) {
}
