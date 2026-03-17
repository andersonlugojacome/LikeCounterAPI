package com.likecounter.api.infrastructure.adapter.in.rest;

import com.likecounter.api.domain.port.in.GetLikeCountUseCase;
import com.likecounter.api.domain.port.in.RegisterLikeUseCase;
import com.likecounter.api.infrastructure.adapter.in.rest.dto.LikeCountResponse;
import com.likecounter.api.infrastructure.adapter.in.rest.dto.LikeRegisteredResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
@Tag(name = "Likes", description = "API para registrar y consultar likes")
public class LikeController {

    private final RegisterLikeUseCase registerLikeUseCase;
    private final GetLikeCountUseCase getLikeCountUseCase;

    public LikeController(RegisterLikeUseCase registerLikeUseCase, GetLikeCountUseCase getLikeCountUseCase) {
        this.registerLikeUseCase = registerLikeUseCase;
        this.getLikeCountUseCase = getLikeCountUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Registrar un like",
            description = "Incrementa en uno el contador acumulado de likes y devuelve el nuevo total."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Like registrado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LikeRegisteredResponse.class),
                            examples = @ExampleObject(
                                    name = "RegistroExitoso",
                                    value = """
                                            {
                                              "message": "Like registrado correctamente",
                                              "count": 6
                                            }
                                            """
                            )
                    )
            )
    })
    public LikeRegisteredResponse registerLike() {
        long count = registerLikeUseCase.registerLike();
        return new LikeRegisteredResponse("Like registrado correctamente", count);
    }

    @GetMapping
    @Operation(
            summary = "Consultar el total actual de likes",
            description = "Obtiene el valor persistido del contador de likes."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Consulta ejecutada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LikeCountResponse.class),
                            examples = @ExampleObject(
                                    name = "ConsultaExitosa",
                                    value = """
                                            {
                                              "count": 5
                                            }
                                            """
                            )
                    )
            )
    })
    public LikeCountResponse getLikes() {
        return new LikeCountResponse(getLikeCountUseCase.getCurrentCount());
    }
}
