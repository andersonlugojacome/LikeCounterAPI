# Analisis profundo de LikeCounterAPI

> Base real usada para este analisis: codigo Java, configuracion, pruebas, documentacion y pipeline presentes en este repositorio. No se inventan archivos.

## 1. Resumen ejecutivo del proyecto

LikeCounterAPI es una API (Application Programming Interface) de tipo REST (Representational State Transfer) para un caso de uso simple y real: registrar likes y consultar el total acumulado.

### Que hace el proyecto

- Expone endpoints para incrementar y consultar un contador de likes.
- Persiste el contador en base de datos.
- Documenta el contrato con OpenAPI (OpenAPI Specification) y Swagger UI (Swagger User Interface).
- Incluye pruebas automaticas y pipeline de CI (Continuous Integration) y CD (Continuous Deployment) hacia Azure Web App.

### Caso de uso principal

- `POST (HTTP POST method) /likes`: incrementa el contador.
- `GET (HTTP GET method) /likes`: devuelve el total actual.

### Problema que resuelve

Resuelve un problema pequeno pero muy didactico: mantener estado de negocio persistente (un contador) mostrando separacion limpia entre dominio, aplicacion e infraestructura.

### Por que es buen proyecto de practica tecnica

- Es pequeno, pero no trivial.
- Demuestra decisiones arquitectonicas reales.
- Tiene trazabilidad completa: codigo, tests, documentacion y despliegue.
- Permite discutir trade-offs profesionales en entrevista.

### Tecnologias principales y para que sirven aqui

| Tecnologia | Rol real en este proyecto |
|---|---|
| Java 17 LTS (Long-Term Support) | Lenguaje base con soporte estable para backend. |
| Maven | Build tool: dependencias, compilacion, pruebas y empaquetado. |
| Spring Boot 3 | Bootstrap de la app, inyeccion de dependencias y auto-configuracion. |
| Spring Web | Exposicion de endpoints REST. |
| Spring Data JPA (Jakarta Persistence API) | Acceso a persistencia por repositorio. |
| H2 (H2 Database Engine) | Base de datos embebida para desarrollo y demo. |
| springdoc-openapi | Generacion de documentacion OpenAPI y Swagger UI. |
| JUnit (JUnit Testing Framework) + MockMvc (Mock Model View Controller) | Pruebas unitarias e integracion de endpoints. |
| GitHub Actions | Ejecucion del pipeline en eventos de git. |
| Azure Web App (Azure App Service Web App) | Hosting del artefacto JAR (Java Archive) desplegado. |

## 2. Explicacion del objetivo funcional

El objetivo funcional es modelar un boton Like de forma persistente y explicable.

### Que hace `POST /likes`

- Recibe una solicitud HTTP (Hypertext Transfer Protocol).
- Invoca el caso de uso de registrar like.
- Carga el contador actual (si no existe, crea uno por defecto en memoria de dominio).
- Incrementa el contador.
- Guarda el nuevo estado.
- Responde con JSON (JavaScript Object Notation), por ejemplo:

```json
{
  "message": "Like registrado correctamente",
  "count": 6
}
```

### Que hace `GET /likes`

- Consulta el valor actual del contador.
- Si no existe registro aun, devuelve 0 desde inicializacion por defecto de dominio.
- Responde JSON:

```json
{
  "count": 5
}
```

### Como se guarda el contador

- Se usa una entidad de persistencia `LikeCounterJpaEntity` mapeada a la tabla `like_counter`.
- El adaptador de salida busca por id fijo `1` y guarda ese unico registro de contador acumulado.
- Se transforma entre modelo de dominio y modelo de persistencia con un mapper dedicado.

### Por que este caso de uso es simple pero util

- Es simple para entender rapido.
- Obliga a resolver estado persistente, no solo logica temporal.
- Permite demostrar puertos de entrada, puertos de salida y adaptadores sin sobre-ingenieria.

## 3. Mapa general del repositorio

| Area | Que contiene | Para que sirve |
|---|---|---|
| `src/main/java` | Dominio, aplicacion e infraestructura | Codigo productivo principal. |
| `src/test/java` | Tests de contexto, controlador y servicio | Validacion automatica de comportamiento. |
| `src/main/resources` | `application.yml` y `public/index.html` | Configuracion y recursos estaticos. |
| `Docs` | Arquitectura y diagramas Mermaid | Material pedagogico y explicativo. |
| `.github/workflows` | `ci-cd.yml` | Build, test y despliegue condicionado a main. |
| `README.md (Read Me)` | Vista general del proyecto | Onboarding rapido tecnico y funcional. |
| `instalador.md` | Guia paso a paso de laboratorio | Aprendizaje guiado para construir y desplegar. |
| `examen.md` | Preguntas y solucionario | Autoevaluacion teorica y practica. |
| `pom.xml` | Dependencias y plugin de build | Definicion del proyecto Maven. |

### Lectura por carpetas de arquitectura

- `domain`: modelo de negocio (`LikeCounter`) y contratos de puertos.
- `application`: servicio de aplicacion (`LikeCounterService`) que ejecuta casos de uso.
- `infrastructure`: adaptadores REST, persistencia JPA/H2 y configuracion OpenAPI.

## 4. Explicacion arquitectonica del proyecto

### Que significa arquitectura hexagonal

Arquitectura hexagonal separa el nucleo de negocio del mundo externo. El negocio se expresa por contratos (puertos) y lo externo se conecta con implementaciones (adaptadores).

### Que son puertos y adaptadores

- Puerto: interfaz que define una interaccion.
- Adaptador: implementacion tecnica de ese puerto.

### Nucleo del negocio en este repo

- Modelo: `LikeCounter`.
- Casos de uso: `RegisterLikeUseCase`, `GetLikeCountUseCase`.
- Orquestacion: `LikeCounterService`.

### Puertos de entrada (inbound)

- `RegisterLikeUseCase`
- `GetLikeCountUseCase`

Definen lo que el sistema "ofrece" hacia afuera.

### Puertos de salida (outbound)

- `LoadLikeCounterPort`
- `SaveLikeCounterPort`

Definen lo que el negocio "necesita" desde afuera.

### Adaptadores de entrada

- `LikeController` (endpoints de likes)
- `HealthController` (saludo `GET /hello`)

Los controladores dependen de puertos de entrada, no de repositorios.

### Adaptadores de salida

- `LikeCounterPersistenceAdapter` implementa `LoadLikeCounterPort` y `SaveLikeCounterPort`.
- Usa `SpringDataLikeCounterRepository` para guardar/cargar.
- Usa `LikeCounterPersistenceMapper` para conversion dominio <-> persistencia.

### Flujo de dependencias de afuera hacia adentro

- REST entra por controlador.
- Controlador llama puerto de entrada.
- Servicio de aplicacion implementa puerto de entrada.
- Servicio llama puertos de salida.
- Adaptador de salida implementa puertos de salida.

El dominio no conoce ni Spring, ni JPA, ni H2.

### Por que mejora mantenibilidad, testabilidad y claridad

- Mantenibilidad: puedes cambiar persistencia tocando adaptadores out.
- Testabilidad: puedes probar servicio con mocks de puertos.
- Claridad: responsabilidades quedan separadas y visibles.

## 5. Explicacion de DDD (Domain-Driven Design) aplicado aqui

### Que es el dominio en este sistema

El dominio es la gestion del contador de likes: estado, regla de incremento y proteccion de invariantes.

### Entidad principal

- `LikeCounter`: tiene identidad (`id`) y estado mutable (`count`).

### Reglas de negocio que existen hoy

- El contador no puede crearse con valor negativo.
- Registrar un like incrementa en uno.
- Si no hay dato persistido, se inicializa contador por defecto.

### Por que `LikeCounter` pertenece al dominio

Porque modela comportamiento de negocio (`registerLike`) y no detalles tecnicos de transporte o base de datos.

### Que esta en dominio, aplicacion e infraestructura

- Dominio: `LikeCounter` + interfaces de puertos.
- Aplicacion: `LikeCounterService` (orquesta casos de uso).
- Infraestructura: controladores, DTO (Data Transfer Object), mapper, entidad JPA, repositorio, configuracion.

### Por que este enfoque es suficiente para una prueba tecnica

- Muestra separacion correcta sin complejidad artificial.
- Permite explicar decisiones y extensibilidad.
- Evita mezclar framework con regla de negocio.

## 6. Recorrido completo del flujo de una peticion

### Flujo de `POST /likes`

1. Cliente envia `POST /likes`.
2. `LikeController.registerLike()` recibe la solicitud.
3. El controlador invoca `RegisterLikeUseCase.registerLike()`.
4. `LikeCounterService` ejecuta el caso de uso.
5. El servicio pide estado actual via `LoadLikeCounterPort.load()`.
6. `LikeCounterPersistenceAdapter` consulta `SpringDataLikeCounterRepository.findById(1L)`.
7. Mapper convierte entidad JPA a dominio.
8. Servicio invoca `likeCounter.registerLike()` (regla de negocio).
9. Servicio persiste via `SaveLikeCounterPort.save(...)`.
10. Adaptador guarda con repository + mapper.
11. Servicio devuelve el conteo actualizado.
12. Controlador construye `LikeRegisteredResponse` y responde `201 Created`.

### Flujo de `GET /likes`

1. Cliente envia `GET /likes`.
2. `LikeController.getLikes()` recibe la solicitud.
3. Invoca `GetLikeCountUseCase.getCurrentCount()`.
4. `LikeCounterService` carga estado via `LoadLikeCounterPort`.
5. Si no existe, usa `LikeCounter.initializeDefault()`.
6. Devuelve `count` actual.
7. Controlador responde `LikeCountResponse` con estado `200`.

### Papel de cada pieza en una frase

- Controlador: traduce HTTP a caso de uso.
- Caso de uso: contrato del comportamiento esperado.
- Servicio de aplicacion: coordina el flujo.
- Puerto: frontera de dependencia.
- Adaptador de persistencia: implementacion tecnica concreta.
- Repositorio: operaciones de lectura/escritura JPA.
- Base de datos: estado persistente del contador.
- DTO: contrato de respuesta para clientes.

## 7. Explicacion archivo por archivo de las piezas clave

| Archivo | Responsabilidad real | Relacion principal | Decision arquitectonica representada |
|---|---|---|---|
| `src/main/java/com/likecounter/api/LikeCounterApiApplication.java` | Punto de arranque Spring Boot. | Inicia el contexto y escaneo de beans. | El framework se concentra en bootstrap, no en el dominio. |
| `src/main/java/com/likecounter/api/domain/model/LikeCounter.java` | Entidad de dominio con estado y comportamiento. | Usada por servicio y mapper. | Regla de negocio encapsulada en modelo de dominio. |
| `src/main/java/com/likecounter/api/domain/port/in/RegisterLikeUseCase.java` | Contrato para registrar like. | Implementado por `LikeCounterService`. | Puerto de entrada desacoplado de REST. |
| `src/main/java/com/likecounter/api/domain/port/in/GetLikeCountUseCase.java` | Contrato para consultar contador. | Implementado por `LikeCounterService`. | Caso de uso expuesto como interfaz del dominio/aplicacion. |
| `src/main/java/com/likecounter/api/domain/port/out/LoadLikeCounterPort.java` | Contrato para cargar contador. | Implementado por adaptador de persistencia. | Dominio define necesidad sin conocer tecnologia. |
| `src/main/java/com/likecounter/api/domain/port/out/SaveLikeCounterPort.java` | Contrato para guardar contador. | Implementado por adaptador de persistencia. | Dependencia invertida hacia infraestructura. |
| `src/main/java/com/likecounter/api/application/service/LikeCounterService.java` | Orquesta carga, incremento y guardado. | Usa puertos out e implementa puertos in. | Capa de aplicacion separada del transporte y base de datos. |
| `src/main/java/com/likecounter/api/infrastructure/adapter/in/rest/LikeController.java` | Endpoints `/likes` GET y POST. | Depende de puertos in + DTOs. | Adaptador de entrada REST sin logica de negocio pesada. |
| `src/main/java/com/likecounter/api/infrastructure/adapter/in/rest/HealthController.java` | Endpoint `/hello`. | Independiente del caso de uso Like. | Endpoint util de verificacion y demo. |
| `src/main/java/com/likecounter/api/infrastructure/adapter/in/rest/dto/LikeCountResponse.java` | DTO de salida para lectura. | Usado por `LikeController.getLikes()`. | Contrato HTTP separado del dominio. |
| `src/main/java/com/likecounter/api/infrastructure/adapter/in/rest/dto/LikeRegisteredResponse.java` | DTO de salida para registro. | Usado por `LikeController.registerLike()`. | Respuesta explicita para cliente API. |
| `src/main/java/com/likecounter/api/infrastructure/adapter/out/persistence/LikeCounterPersistenceAdapter.java` | Implementa puertos out con JPA. | Usa repository y mapper. | Adaptador secundario de persistencia. |
| `src/main/java/com/likecounter/api/infrastructure/adapter/out/persistence/entity/LikeCounterJpaEntity.java` | Modelo de persistencia JPA (`like_counter`). | Consumido por repository y mapper. | Separacion dominio vs persistencia. |
| `src/main/java/com/likecounter/api/infrastructure/adapter/out/persistence/mapper/LikeCounterPersistenceMapper.java` | Traduce dominio <-> entidad JPA. | Conecta `LikeCounter` y `LikeCounterJpaEntity`. | Evita contaminar dominio con anotaciones JPA. |
| `src/main/java/com/likecounter/api/infrastructure/adapter/out/persistence/repository/SpringDataLikeCounterRepository.java` | Repositorio Spring Data para CRUD (Create, Read, Update, Delete) basico. | Extiende `JpaRepository`. | Aprovecha abstraccion Spring sin exponerla al dominio. |
| `src/main/java/com/likecounter/api/infrastructure/config/OpenApiConfig.java` | Metadatos globales OpenAPI y bean base. | Afecta Swagger y docs JSON. | Documentacion como parte de infraestructura. |
| `src/main/resources/application.yml` | Config de datasource, JPA, actuator, springdoc y perfiles. | Leida por Spring Boot al iniciar. | Config declarativa centralizada. |
| `.github/workflows/ci-cd.yml` | Pipeline de build/test/deploy a Azure. | Ejecutado por GitHub Actions. | Governance: validar siempre, desplegar solo en main. |

## 8. Explicacion del dominio y de la entidad principal

### Por que `LikeCounter` es entidad de dominio

- Tiene identidad (`id`) que distingue una instancia de otra.
- Tiene estado de negocio (`count`).
- Tiene comportamiento de negocio (`registerLike`).

### Estado

- `id: Long`
- `count: long`

### Comportamiento

- `initializeDefault()` crea estado inicial consistente.
- `registerLike()` incrementa contador en uno.

### Regla que protege

En el constructor, `count < 0` lanza `IllegalArgumentException`. Esto protege una invariante basica: no hay likes negativos.

### Por que incrementar debe vivir ahi

Porque la regla de negocio debe estar donde vive el estado. Si incrementaras fuera del dominio, terminarias con logica duplicada y mayor riesgo de inconsistencias.

## 9. Explicacion de persistencia con H2 (H2 Database Engine)

### Que es H2

H2 es una base de datos relacional embebida, ideal para pruebas tecnicas y desarrollo local rapido.

### Por que se eligio aqui

- Cero friccion para arrancar.
- Integracion directa con Spring Data JPA.
- Facil para demo y aprendizaje.

### Como esta configurado en `application.yml`

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:likecounterdb
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    hibernate:
      ddl-auto: update
```

### Diferencia entre perfil normal y `prod`

- Perfil por defecto: `jdbc:h2:mem:likecounterdb` (en memoria, se pierde al reiniciar).
- Perfil `prod`: `jdbc:h2:file:./data/likecounterdb` (archivo local, persiste entre reinicios).
- En `prod`, la consola H2 queda deshabilitada.

### Como se guarda el contador

- `LikeCounterPersistenceAdapter` lee por `id = 1`.
- Si hay registro, lo mapea a dominio.
- Al guardar, convierte dominio a entidad JPA y persiste.

### Que tabla se genera

- Tabla logica: `like_counter`.
- En consola H2 suele verse como `LIKE_COUNTER` por convencion de visualizacion.

### Como inspeccionar data manualmente

1. Levantar app con `mvn spring-boot:run`.
2. Abrir URL (Uniform Resource Locator): `http://localhost:8080/h2-console`.
3. Usar JDBC (Java Database Connectivity) URL `jdbc:h2:mem:likecounterdb`, user `sa`, password vacio.
4. Ejecutar SQL (Structured Query Language):

```sql
SELECT * FROM like_counter;
```

## 10. Explicacion de Spring Boot dentro de esta solucion

### Que hace Spring Boot aqui

- Arranca servidor embebido.
- Crea beans y resuelve inyeccion de dependencias.
- Aplica auto-configuracion para web, JPA, actuator y springdoc.

### Que resuelve automaticamente

- Wiring de controladores, servicios y componentes por anotaciones.
- Integracion datasource/JPA con configuracion declarativa.
- Exposicion de endpoints de salud (`/actuator/health`).

### Como ayuda con REST, inyeccion y configuracion

- `@RestController`, `@GetMapping`, `@PostMapping` para capa web.
- Inyeccion por constructor para dependencias.
- `application.yml` para parametros de entorno.

### Por que el dominio sigue desacoplado

Porque `LikeCounter` y puertos de dominio no dependen de anotaciones Spring ni tipos JPA. Spring aparece en aplicacion/infraestructura, no en el modelo central.

## 11. Explicacion de Maven y `pom.xml`

### Que es Maven

Maven es una herramienta de build y gestion de dependencias para proyectos Java.

### Papel en este proyecto

- Define dependencias de runtime y test.
- Ejecuta compilacion, pruebas y empaquetado.
- Integra plugin de Spring Boot para ejecutar/empaquetar app.

### Dependencias principales y para que sirven

| Dependencia en `pom.xml` | Uso en LikeCounterAPI |
|---|---|
| `spring-boot-starter-web` | Capa web REST. |
| `spring-boot-starter-data-jpa` | Persistencia con JPA. |
| `spring-boot-starter-validation` | Validacion declarativa futura (base preparada). |
| `spring-boot-starter-actuator` | Salud y observabilidad basica. |
| `com.h2database:h2` | Base embebida en runtime. |
| `springdoc-openapi-starter-webmvc-ui` | OpenAPI + Swagger UI. |
| `spring-boot-starter-test` | Stack de pruebas (JUnit, Mockito, Spring Test). |

### Que hace cada comando clave

- `mvn spring-boot:run`: compila y ejecuta localmente.
- `mvn test`: ejecuta pruebas sin empaquetar artefacto final.
- `mvn clean verify`: limpia, compila, prueba y verifica ciclo completo.
- `mvn clean package -DskipTests`: genera JAR omitiendo pruebas (usado en etapa de deploy cuando ya hubo validacion previa).

## 12. Explicacion de OpenAPI (OpenAPI Specification) y Swagger UI (Swagger User Interface)

### Como estan implementados en este proyecto

- Dependencia `springdoc-openapi-starter-webmvc-ui` en `pom.xml`.
- Config global en `OpenApiConfig` con `@OpenAPIDefinition`.
- Documentacion por endpoint con `@Operation` y `@ApiResponse` en `LikeController`.
- Documentacion de payloads con `@Schema` en DTOs.

### Que aporta `OpenApiConfig`

- Metadatos de API (titulo, version, descripcion, contacto).
- Servidores declarados (local y Azure de ejemplo).
- Bean `OpenAPI` base para inicializar el modelo.

### Rutas utiles para probar documentacion

- `GET /` (pagina publica de accesos rapidos).
- `GET /swagger-ui.html`.
- `GET /api-docs`.

### Valor didactico y de consumo

- Facilita probar la API sin Postman obligatorio.
- Sirve como contrato vivo para frontend e integraciones.
- Mejora onboarding de nuevos miembros del equipo.

## 13. Explicacion del testing

### Que pruebas existen hoy

- `LikeCounterApiApplicationTests`: prueba de contexto (`contextLoads`).
- `LikeControllerTest`: pruebas de integracion web.
- `LikeCounterServiceTest`: prueba unitaria de servicio de aplicacion.

### Que valida cada una

| Prueba | Validacion principal |
|---|---|
| Contexto | Que Spring Boot arranca sin errores de configuracion. |
| Controlador GET/POST | Codigos HTTP, payload JSON y flujo basico de endpoints. |
| Controlador docs/public | `/`, `/index.html` y `/api-docs` disponibles. |
| Servicio aplicacion | Regla de incremento y retorno de conteo con puertos mockeados. |

### Diferencia entre context, controller y service tests

- Contexto: sanidad de arranque del contenedor Spring.
- Controlador: comportamiento externo observable por HTTP.
- Servicio: logica de aplicacion aislada de infraestructura real.

### Por que JUnit y MockMvc son adecuados

- JUnit integra bien con Spring Boot Test.
- MockMvc permite validar rutas y respuestas sin levantar servidor externo manual.

### Cobertura conceptual lograda

- Flujo principal de negocio cubierto.
- Contrato HTTP basico cubierto.
- Integracion con documentacion y recurso publico cubierta.

### Que faltaria si creciera el proyecto

- Tests de concurrencia para incremento simultaneo.
- Tests de errores y validaciones de entrada.
- Tests de repositorio con escenarios mas ricos.
- Tests de contrato y pruebas de seguridad/autorizacion.

## 14. Explicacion del pipeline CI (Continuous Integration) / CD (Continuous Deployment)

El pipeline esta en `.github/workflows/ci-cd.yml`, un archivo YAML (YAML Ain't Markup Language).

### Eventos que lo disparan

- `pull_request` hacia `main`.
- `push` hacia `main`.

### Por que hay validacion en `pull_request`

Porque permite detectar fallos antes de integrar cambios al tronco estable.

### Por que deploy solo en `push` a `main`

Porque `main` representa codigo integrado. Desplegar desde ramas feature incrementa riesgo operativo.

### Que hace cada job

- `build-and-test`: checkout, Java 17, `mvn clean verify`.
- `deploy-to-azure`: solo si push a main y si `build-and-test` paso; luego empaqueta y despliega.

### Que hace cada step relevante

1. Checkout del repo.
2. Setup de Java 17 con cache Maven.
3. Build/test o package segun job.
4. Deploy por script Python + `curl` a Kudu.

### Por que `build-and-test` va antes de `deploy-to-azure`

`deploy-to-azure` tiene `needs: build-and-test`. Es una puerta de calidad: no hay despliegue si falla compilacion o test.

### Significado de la condicion

```yaml
if: github.event_name == 'push' && github.ref == 'refs/heads/main'
```

Significa: ejecutar el job de deploy unicamente cuando el evento sea push y la referencia sea la rama main.

### Por que esta estrategia es profesional y segura

- Separa validacion tecnica de liberacion.
- Reduce despliegues accidentales.
- Mantiene una politica clara de promocion de cambios.

## 15. Explicacion del despliegue en Azure

### Por que Azure Web App es razonable aqui

- Servicio administrado simple para apps Java.
- Menor complejidad que orquestadores para una demo.
- Integra bien con GitHub Actions.

### Secretos necesarios

Realidad del repo actual:

- El workflow consume directamente `AZURE_WEBAPP_PUBLISH_PROFILE`.

Documentacion del proyecto:

- README e instalador tambien mencionan `AZURE_WEBAPP_NAME` como dato recomendado de configuracion operativa.

### Para que sirve `AZURE_WEBAPP_PUBLISH_PROFILE`

Contiene credenciales de publicacion (incluyendo endpoint SCM (Source Control Management)/Kudu, usuario y password) para autenticar el despliegue.

### Como funciona deploy con Kudu Publish API (Kudu Deployment API)

1. El script lee XML (Extensible Markup Language) del publish profile.
2. Busca el perfil SCM.
3. Construye URL de deploy `/api/publish?type=jar&clean=true&restart=true`.
4. Selecciona el JAR de `target/` (excluyendo `.original`).
5. Envia artefacto con `curl` autenticado.

### Por que no usa `azure/webapps-deploy`

Segun README y comentarios del workflow, se evita por advertencias de runtime Node 20 en esa action. El repo opta por Kudu directo para mantener control y evitar ese warning.

### Como verificar despliegue

- Revisar job `deploy-to-azure` en GitHub Actions.
- Abrir URL publica de la app.
- Probar:
  - `GET /likes`
  - `POST /likes`
  - `GET /actuator/health`
  - `GET /swagger-ui.html`

## 16. Relacion entre teoria y practica

| Concepto | Implementacion real en LikeCounterAPI | Resultado practico |
|---|---|---|
| DDD | Entidad `LikeCounter` y puertos en dominio | Reglas de negocio claras y centradas. |
| Arquitectura hexagonal | Puertos in/out + adaptadores REST/JPA | Cambios tecnicos con menor impacto en negocio. |
| Puertos y adaptadores | Interfaces en dominio, implementaciones en infraestructura | Inversion de dependencias aplicada. |
| REST | `LikeController` con endpoints HTTP | Consumo simple por clientes y herramientas. |
| DTO | `LikeCountResponse`, `LikeRegisteredResponse` | Contratos de respuesta explicitos. |
| JPA | `LikeCounterJpaEntity` + `SpringDataLikeCounterRepository` | Persistencia rapida sin SQL manual para operaciones basicas. |
| Testing | JUnit + MockMvc + Mockito | Validacion de contexto, API y logica. |
| CI/CD | Workflow `ci-cd.yml` con gates de calidad | Integracion continua y despliegue controlado. |
| Azure | Deploy JAR por Kudu a Web App | Publicacion cloud automatizada. |

## 17. Ventajas, decisiones y trade-offs

### Decisiones acertadas

- Separacion dominio/aplicacion/infraestructura clara.
- Puertos bien definidos para in y out.
- Mapper explicito dominio <-> persistencia.
- Pipeline con despliegue condicionado a main.
- Documentacion tecnica y pedagogica abundante.

### Simplificaciones hechas a proposito

- Un solo contador con id fijo `1`.
- Sin autenticacion/autorizacion.
- Sin versionado de API.
- Sin base externa administrada (se usa H2).

### Alternativas que existian

- Usar PostgreSQL en vez de H2.
- Usar Flyway/Liquibase para migraciones.
- Usar `azure/webapps-deploy` en vez de Kudu script.
- Unificar modelo dominio y entidad JPA (menos capas, mas acople).

### Mejoras futuras posibles

- Manejo de concurrencia optimista/pesimista.
- Endpoints con idempotencia o llaves de deduplicacion para eventos.
- Observabilidad con metricas y trazas.
- Seguridad con autenticacion y autorizacion.

### Que esta bien para prueba tecnica y en grande seria distinto

- En prueba tecnica, H2 y un contador global son correctos.
- En sistema grande, se necesitaria base de datos robusta, control de concurrencia, seguridad, versionado de contratos y migraciones formales.

## 18. Riesgos, limites y oportunidades de mejora

### Limitaciones actuales

- Modelo de un solo contador global.
- Persistencia local no distribuida.
- Sin control de concurrencia explicito en incremento.
- Sin mecanismos de seguridad.

### Riesgos si creciera a produccion real

- Condiciones de carrera con alta simultaneidad.
- Perdida de disponibilidad o consistencia en cambios de entorno.
- Exposicion de endpoints sin proteccion.
- Dificultad de auditoria por falta de eventos historicos.

### Mejoras prioritarias

1. Cambiar a base de datos gestionada (por ejemplo Azure SQL o PostgreSQL).
2. Agregar control de concurrencia en escritura.
3. Incorporar autenticacion y autorizacion.
4. Introducir migraciones versionadas de esquema.
5. Fortalecer estrategia de pruebas (concurrencia, errores, seguridad).

### Siguientes pasos tecnicos recomendados

- Evolucionar de contador global a contador por recurso (post, comentario, etc.).
- Agregar endpoint de historial de eventos.
- Incorporar validaciones de contrato en pipeline.

## 19. Guia para defender este proyecto en entrevista

### Como resumir en 1 minuto

"LikeCounterAPI es una API REST en Java 17 y Spring Boot que implementa un caso de negocio simple pero completo: registrar y consultar likes persistidos. Esta construida con arquitectura hexagonal y enfoque DDD para separar dominio de infraestructura. Tiene pruebas automaticas, documentacion OpenAPI/Swagger y pipeline CI/CD que valida en pull request y despliega solo al integrar a main en Azure Web App."

### Como explicarlo en 3 minutos

1. Problema: contar likes persistentes.
2. Arquitectura: dominio con puertos, aplicacion con servicio, infraestructura con controladores y persistencia.
3. Flujo POST/GET: controlador -> caso de uso -> servicio -> puertos out -> adaptador JPA/H2 -> respuesta DTO.
4. Calidad: pruebas de contexto, integracion y servicio.
5. Operacion: workflow de build/test y deploy condicionado a `push` en `main`.

### Decisiones tecnicas que conviene destacar

- Inversion de dependencias con puertos in/out.
- Dominio sin acoplamiento a Spring/JPA.
- Mapper explicito para separar modelos.
- Deploy protegido por condicion de rama y evento.

### Preguntas dificiles que pueden hacerte

1. "Que pasa con concurrencia en `POST /likes`?"
2. "Por que no usar directamente entidad JPA como dominio?"
3. "Por que H2 y no una base administrada desde el inicio?"
4. "Que cambiarias para escalar esto a produccion?"
5. "Como garantizas que no se despliega codigo roto?"

### Respuestas solidas sugeridas

- Concurrencia: hoy es simplificado para demo; en produccion agregaria versionado optimista/pesimista y pruebas de carga concurrente.
- Entidad JPA = dominio: se puede, pero aumenta acople; aqui se priorizo claridad arquitectonica y testabilidad.
- H2: reduce friccion para prueba tecnica; la arquitectura permite reemplazar por otro adaptador out.
- Escalado: base de datos gestionada, seguridad, observabilidad, migraciones y hardening del pipeline.
- Calidad de deploy: gate `needs: build-and-test` + condicion estricta de evento/rama.

## Resumen ultra corto

Proyecto backend pequeno pero profesional: API REST de likes con Java 17, Spring Boot, arquitectura hexagonal, DDD, persistencia H2, documentacion OpenAPI/Swagger, pruebas automaticas y despliegue a Azure via GitHub Actions solo desde main.

## Lo mas importante para recordar

- El dominio manda: `LikeCounter` contiene regla y estado.
- Los puertos definen contratos; los adaptadores implementan tecnologia.
- `LikeCounterService` orquesta casos de uso sin acoplarse al framework web.
- El pipeline valida en `pull_request` y despliega solo en `push` a `main`.
- La separacion actual facilita mantenimiento, pruebas y evolucion.

## Preguntas que yo deberia hacerte despues

1. Como implementar control de concurrencia correcto para `POST /likes`?
2. Como migrar de H2 a PostgreSQL sin romper la arquitectura?
3. Como agregar autenticacion JWT (JSON Web Token) manteniendo puertos y adaptadores limpios?
4. Como versionar la API (`/v1`, `/v2`) y evitar breaking changes?
5. Como agregar pruebas de contrato y de carga al pipeline?
6. Como adaptar este diseño para likes por recurso (por post, por usuario)?
