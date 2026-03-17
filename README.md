# LikeCounterAPI

LikeCounterAPI es una API REST construida con Java 17, Maven y Spring Boot para demostrar una implementación simple pero profesional de arquitectura hexagonal, DDD, persistencia con H2, pruebas automáticas y CI/CD con GitHub Actions hacia Azure.

## Objetivo

El caso funcional central es un botón `Like`. Cada clic incrementa un contador persistido en base de datos y la API permite consultar el valor actual.

## Stack tecnológico

- Java 17
- Maven
- Spring Boot 3
- Spring Web
- Spring Data JPA
- H2 Database
- JUnit 5 + MockMvc
- GitHub Actions
- Azure Web App

## Arquitectura

El proyecto separa responsabilidades en:

- `domain`: reglas del negocio y contratos.
- `application`: casos de uso y orquestación.
- `infrastructure`: adaptadores REST, persistencia y configuración.

La lógica del dominio no depende de Spring ni de JPA. Spring Boot vive en la infraestructura y conecta los adaptadores con los puertos.

## Caso de uso Like

- `POST /likes`: registra un like.
- `GET /likes`: devuelve el total actual.
- `GET /hello`: endpoint básico de verificación.
- `GET /actuator/health`: health endpoint.
- `GET /swagger-ui.html`: interfaz Swagger UI.
- `GET /api-docs`: especificación OpenAPI en JSON.
- `GET /`: página pública con accesos rápidos a Swagger/OpenAPI.

## Ejemplos

`GET /likes`

```json
{
  "count": 5
}
```

`POST /likes`

```json
{
  "message": "Like registrado correctamente",
  "count": 6
}
```

## Ejecución local

```bash
mvn spring-boot:run
```

La API queda disponible en `http://localhost:8080`.

## Tests

```bash
mvn test
```

## H2

- Consola: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:likecounterdb`
- Usuario: `sa`
- Password: vacío

La tabla principal es `like_counter`.

## OpenAPI y Swagger

La documentación se construye automáticamente con `springdoc-openapi` a partir de:

- configuración global en anotaciones OpenAPI
- anotaciones en controladores
- anotaciones `@Schema` en DTOs

Rutas útiles:

- `http://localhost:8080/`
- `http://localhost:8080/swagger-ui.html`
- `http://localhost:8080/api-docs`
- `http://localhost:8080/v3/api-docs` si deseas conservar la ruta por defecto de springdoc

## CI/CD

El workflow está en `.github/workflows/ci-cd.yml`.

- En `pull_request` a `main` ejecuta build y tests.
- En `push` a `main` ejecuta build, tests y despliegue.
- El despliegue no ocurre en ramas feature, lo que reduce riesgo y mantiene `main` como fuente desplegable.
- El workflow fuerza la ejecución de acciones JavaScript sobre Node 24 para adelantarse a la migración anunciada por GitHub Actions.

## Azure

Para desplegar necesitas estos secrets en GitHub:

- `AZURE_WEBAPP_NAME`
- `AZURE_WEBAPP_PUBLISH_PROFILE`

La guía paso a paso está en `instalador.md` y el detalle pedagógico extendido en `Docs/arquitectura-y-conceptos.md`.
