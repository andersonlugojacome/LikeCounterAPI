package com.likecounter.api.infrastructure.adapter.in.rest;

import com.likecounter.api.domain.port.in.GetDislikeCountUseCase;
import com.likecounter.api.domain.port.in.RegisterDislikeUseCase;
import com.likecounter.api.infrastructure.adapter.in.rest.dto.DislikeCountResponse;
import com.likecounter.api.infrastructure.adapter.in.rest.dto.DislikeRegisteredResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RequestMapping("/dislikes")
@Tag(name = "Dislikes", description = "API para registrar y consultar dislikes")
public class DislikeController {

    private final RegisterDislikeUseCase registerDislikeUseCase;
    private final GetDislikeCountUseCase getDislikeCountUseCase;

    public DislikeController(RegisterDislikeUseCase registerDislikeUseCase, GetDislikeCountUseCase getDislikeCountUseCase) {
        this.registerDislikeUseCase = registerDislikeUseCase;
        this.getDislikeCountUseCase = getDislikeCountUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Registrar un dislike",
            description = "Incrementa en uno el contador acumulado de dislikes y devuelve el nuevo total."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Dislike registrado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DislikeRegisteredResponse.class),
                            examples = @ExampleObject(
                                    name = "RegistroExitoso",
                                    value = """
                                            {
                                              "message": "Dislike registrado correctamente",
                                              "count": 6
                                            }
                                            """
                            )
                    )
            )
    })
    public DislikeRegisteredResponse registerDislike() {
        long count = registerDislikeUseCase.registerDislike();
        return new DislikeRegisteredResponse("Dislike registrado correctamente", count);
    }

    @GetMapping
    @Operation(
            summary = "Consultar el total actual de dislikes",
            description = "Obtiene el valor persistido del contador de dislikes."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Consulta ejecutada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DislikeCountResponse.class),
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
    public DislikeCountResponse getDislikes() {
        return new DislikeCountResponse(getDislikeCountUseCase.getCurrentCount());
    }
}
