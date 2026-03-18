# Prompt para analizar LikeCounterAPI a profundidad

Quiero que actúes como arquitecto de software senior, backend engineer senior, DevOps engineer senior y mentor pedagógico experto en Java 17, Maven, Spring Boot, arquitectura hexagonal, DDD, H2, OpenAPI, testing, GitHub Actions, CI/CD y despliegue en Azure.

Tu tarea es analizar y explicarme a profundidad este proyecto real llamado `LikeCounterAPI`.

No quiero una explicación genérica. Quiero que tomes como base el contenido real del proyecto, su estructura, sus archivos, sus decisiones técnicas y su flujo completo.

## Objetivo de la respuesta

Necesito entender este proyecto de punta a punta, como si estuviera estudiándolo para:

- defenderlo en una entrevista técnica
- explicarlo en una presentación
- mantenerlo y extenderlo
- aprender arquitectura hexagonal y DDD sin sobreingeniería
- entender cómo se conecta backend, persistencia, documentación, pruebas y despliegue

## Regla obligatoria sobre abreviaturas

Cada vez que uses una abreviatura o sigla por primera vez, debes escribir su significado completo en inglés entre paréntesis.

Ejemplos:

- `CI (Continuous Integration)`
- `CD (Continuous Deployment)`
- `DDD (Domain-Driven Design)`
- `API (Application Programming Interface)`
- `REST (Representational State Transfer)`
- `DTO (Data Transfer Object)`
- `JPA (Jakarta Persistence API)`
- `H2 (H2 Database Engine)`
- `YAML (YAML Ain't Markup Language)`
- `HTTP (Hypertext Transfer Protocol)`
- `JSON (JavaScript Object Notation)`
- `PR (Pull Request)`

Si luego vuelves a usar la abreviatura, ya puedes usar solo la sigla.

## Forma de responder

Quiero una explicación:

- profunda pero clara
- pedagógica
- ordenada por secciones
- con lenguaje sencillo cuando el concepto sea complejo
- conectando teoría con este proyecto real
- indicando siempre el porqué de cada decisión técnica

No asumas que ya entiendo los conceptos. Enséñame como mentor, pero sin caer en explicaciones superficiales.

## Estructura obligatoria de tu respuesta

Desarrolla la respuesta exactamente en este orden:

### 1. Resumen ejecutivo del proyecto

Explícame:

- qué hace este proyecto
- cuál es su caso de uso principal
- qué problema resuelve
- por qué es un buen proyecto de práctica técnica
- cuáles son las tecnologías principales y para qué sirve cada una dentro de este proyecto

### 2. Explicación del objetivo funcional

Explícame en detalle el caso de uso del botón `Like`:

- qué hace `POST /likes`
- qué hace `GET /likes`
- cómo se guarda el contador
- por qué este caso de uso es simple pero útil para demostrar arquitectura

### 3. Mapa general del repositorio

Quiero que recorras la estructura del proyecto y expliques para qué sirve cada área principal.

Debes cubrir como mínimo:

- `src/main/java`
- `src/test/java`
- `src/main/resources`
- `Docs`
- `.github/workflows`
- `README.md`
- `instalador.md`
- `examen.md`
- `pom.xml`

### 4. Explicación arquitectónica del proyecto

Explícame cómo está aplicada la arquitectura hexagonal en este proyecto real.

Quiero que me digas:

- qué significa arquitectura hexagonal
- qué son puertos y adaptadores
- cuál es el núcleo del negocio
- cuáles son los puertos de entrada
- cuáles son los puertos de salida
- cuáles son los adaptadores de entrada
- cuáles son los adaptadores de salida
- cómo fluye la dependencia de afuera hacia adentro
- por qué esta separación mejora mantenibilidad, testabilidad y claridad

### 5. Explicación de DDD aplicado aquí

Explícame cómo se usa `DDD (Domain-Driven Design)` en este proyecto sin complicarlo de más.

Quiero que desarrolles:

- qué es el dominio en este sistema
- cuál es la entidad principal
- cuáles son las reglas de negocio
- por qué `LikeCounter` pertenece al dominio
- qué está en dominio, qué está en aplicación y qué está en infraestructura
- por qué este enfoque es suficiente para una prueba técnica profesional

### 6. Recorrido completo del flujo de una petición

Explícame paso a paso qué pasa cuando un cliente hace:

- `POST /likes`
- `GET /likes`

Quiero el recorrido completo desde que entra la petición hasta que vuelve la respuesta.

Debes mencionar el papel de:

- controlador
- caso de uso
- servicio de aplicación
- puertos
- adaptador de persistencia
- repositorio
- base de datos
- DTOs

### 7. Explicación archivo por archivo de las piezas clave

Quiero que analices y expliques el propósito de los archivos clave del proyecto, indicando:

- qué responsabilidad tiene cada archivo
- por qué existe
- con qué otros archivos se relaciona
- qué decisión arquitectónica representa

Debes cubrir como mínimo estos archivos o equivalentes reales del proyecto:

- `src/main/java/com/likecounter/api/LikeCounterApiApplication.java`
- `src/main/java/com/likecounter/api/domain/model/LikeCounter.java`
- `src/main/java/com/likecounter/api/domain/port/in/RegisterLikeUseCase.java`
- `src/main/java/com/likecounter/api/domain/port/in/GetLikeCountUseCase.java`
- `src/main/java/com/likecounter/api/domain/port/out/LoadLikeCounterPort.java`
- `src/main/java/com/likecounter/api/domain/port/out/SaveLikeCounterPort.java`
- `src/main/java/com/likecounter/api/application/service/LikeCounterService.java`
- `src/main/java/com/likecounter/api/infrastructure/adapter/in/rest/LikeController.java`
- `src/main/java/com/likecounter/api/infrastructure/adapter/in/rest/HealthController.java`
- `src/main/java/com/likecounter/api/infrastructure/adapter/in/rest/dto/LikeCountResponse.java`
- `src/main/java/com/likecounter/api/infrastructure/adapter/in/rest/dto/LikeRegisteredResponse.java`
- `src/main/java/com/likecounter/api/infrastructure/adapter/out/persistence/LikeCounterPersistenceAdapter.java`
- `src/main/java/com/likecounter/api/infrastructure/adapter/out/persistence/entity/LikeCounterJpaEntity.java`
- `src/main/java/com/likecounter/api/infrastructure/adapter/out/persistence/mapper/LikeCounterPersistenceMapper.java`
- `src/main/java/com/likecounter/api/infrastructure/adapter/out/persistence/repository/SpringDataLikeCounterRepository.java`
- `src/main/java/com/likecounter/api/infrastructure/config/OpenApiConfig.java`
- `src/main/resources/application.yml`
- `.github/workflows/ci-cd.yml`

### 8. Explicación del dominio y de la entidad principal

Haz una sección especial sobre `LikeCounter` y explícame:

- por qué es una entidad de dominio
- cuál es su estado
- cuál es su comportamiento
- qué regla protege
- por qué la lógica de incrementar debe vivir ahí o estar muy cerca del dominio

### 9. Explicación de persistencia con H2

Explícame:

- qué es `H2 (H2 Database Engine)`
- por qué se eligió aquí
- cómo está configurado en `application.yml`
- qué diferencia hay entre perfil normal y perfil `prod`
- cómo se guarda el contador
- qué tabla se genera
- cómo inspeccionar la data manualmente
- cómo entrar a la consola H2

### 10. Explicación de Spring Boot dentro de esta solución

Explícame con aterrizaje a este proyecto:

- qué hace Spring Boot aquí
- qué resuelve automáticamente
- cómo ayuda con REST, inyección de dependencias y configuración
- por qué el dominio sigue desacoplado aunque el proyecto use Spring

### 11. Explicación de Maven y `pom.xml`

Quiero que expliques:

- qué es Maven
- qué papel cumple en este proyecto
- qué dependencias principales se usan y para qué sirve cada una
- qué hacen comandos como:
  - `mvn spring-boot:run`
  - `mvn test`
  - `mvn clean verify`
  - `mvn clean package -DskipTests`

### 12. Explicación de OpenAPI y Swagger

Explícame:

- qué es `OpenAPI (OpenAPI Specification)`
- qué es `Swagger UI (Swagger User Interface)`
- cómo están implementados en este proyecto
- qué aporta `OpenApiConfig`
- qué rutas se pueden usar para probar la documentación
- por qué esto mejora la experiencia de aprendizaje y consumo de la API

### 13. Explicación del testing

Quiero que analices la estrategia de pruebas del proyecto y expliques:

- qué pruebas existen
- qué valida cada una
- qué diferencia hay entre pruebas de contexto, pruebas de controlador y pruebas del servicio de aplicación
- por qué `JUnit (JUnit Testing Framework)` y `MockMvc (Mock Model View Controller)` son adecuados aquí
- qué cobertura conceptual se logra
- qué faltaría si el proyecto creciera

### 14. Explicación del pipeline CI/CD

Analiza el archivo `.github/workflows/ci-cd.yml` y explícame con detalle:

- qué eventos lo disparan
- por qué hay validación en `pull_request`
- por qué el deploy ocurre solo en `push` a `main`
- qué hace cada job
- qué hace cada step
- por qué `build-and-test` va antes que `deploy-to-azure`
- qué significa la condición:
  `if: github.event_name == 'push' && github.ref == 'refs/heads/main'`
- por qué esta estrategia es profesional y segura

### 15. Explicación del despliegue en Azure

Quiero que expliques:

- por qué `Azure Web App (Azure App Service Web App)` es una opción razonable aquí
- qué secretos se necesitan
- para qué sirve `AZURE_WEBAPP_PUBLISH_PROFILE`
- cómo funciona el despliegue usando `Kudu Publish API (Kudu Deployment API)`
- por qué este proyecto no usa `azure/webapps-deploy`
- cómo verificar si el despliegue salió bien

### 16. Relación entre teoría y práctica

Quiero una sección donde conectes los conceptos con el proyecto real:

- DDD
- arquitectura hexagonal
- puertos y adaptadores
- REST
- DTO
- JPA
- testing
- CI/CD
- Azure

Explícame cómo todas esas piezas se integran en una sola solución coherente.

### 17. Ventajas, decisiones y trade-offs

Explícame:

- qué decisiones fueron acertadas
- qué simplificaciones se hicieron a propósito
- qué alternativas existían
- qué mejoras futuras podrían hacerse
- qué partes están bien para una prueba técnica aunque en un sistema grande se harían distinto

### 18. Riesgos, límites y oportunidades de mejora

Quiero que indiques:

- limitaciones actuales del proyecto
- posibles riesgos si esto creciera a producción real
- mejoras prioritarias
- posibles siguientes pasos técnicos

### 19. Guía para defender este proyecto en entrevista

Dame una sección final con:

- cómo resumir este proyecto en 1 minuto
- cómo explicarlo en 3 minutos
- qué decisiones técnicas conviene destacar
- qué preguntas difíciles podrían hacerme
- cómo responderlas con solidez

## Requisitos adicionales de calidad

Además, quiero que cumplas estas reglas:

- no inventes archivos que no existan
- si mencionas un archivo, relaciónalo con su responsabilidad real
- si infieres algo, dilo claramente
- explica conceptos técnicos con precisión
- conecta siempre teoría con implementación real
- no des una respuesta corta: quiero profundidad real
- usa tablas cuando aporten claridad
- usa listas cuando aporten orden
- usa ejemplos concretos con este proyecto

## Contexto real del proyecto a considerar

Toma como base este contexto:

- Proyecto: `LikeCounterAPI`
- Stack principal: Java 17, Maven, Spring Boot 3, Spring Web, Spring Data JPA, H2, JUnit, MockMvc, GitHub Actions, Azure Web App
- Caso funcional central: contador de likes
- Endpoints principales:
  - `POST /likes`
  - `GET /likes`
  - `GET /hello`
  - `GET /actuator/health`
  - `GET /swagger-ui.html`
  - `GET /api-docs`
- Arquitectura por capas:
  - `domain`
  - `application`
  - `infrastructure`
- Documentación disponible en:
  - `README.md`
  - `Docs/arquitectura-y-conceptos.md`
  - `Docs/diagrama-flujo-like.md`
  - `Docs/diagrama-clases-like.md`
  - `instalador.md`
  - `examen.md`
- Persistencia:
  - H2 en memoria por defecto
  - H2 en archivo para perfil `prod`
- CI/CD:
  - valida en `pull_request` a `main`
  - despliega solo en `push` a `main`
  - usa Kudu Publish API para desplegar el `.jar` en Azure

## Formato final deseado

Quiero que cierres tu respuesta con estas 3 secciones:

1. `Resumen ultra corto`
   Una síntesis ejecutiva del proyecto en pocas líneas.

2. `Lo más importante para recordar`
   Los aprendizajes clave que debería memorizar.

3. `Preguntas que yo debería hacerte después`
   Una lista de preguntas recomendadas para seguir profundizando.
