package com.likecounter.api.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "LikeCounterAPI",
                version = "v1",
                description = "API de ejemplo para demostrar DDD, arquitectura hexagonal, H2 y CI/CD.",
                contact = @Contact(name = "LikeCounterAPI Team")
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Entorno local"),
                @Server(url = "https://<tu-app>.azurewebsites.net", description = "Entorno Azure Web App")
        }
)
public class OpenApiConfig {

    @Bean
    public io.swagger.v3.oas.models.OpenAPI likeCounterOpenApi() {
        return new io.swagger.v3.oas.models.OpenAPI();
    }
}
