# Arquitectura y conceptos de LikeCounterAPI

## Resumen de la solución

Se construyó una API REST con un caso funcional simple pero real: contar likes. La solución usa Java 17, Maven, Spring Boot, H2, pruebas automáticas y GitHub Actions. La lógica del negocio se mantuvo aislada del framework y de la base de datos mediante arquitectura hexagonal y DDD simplificado.

## Spring Boot

Spring Boot acelera el arranque de aplicaciones Java porque ofrece auto-configuración, servidor embebido y convenciones útiles. En este proyecto permite exponer la API REST, conectar JPA y arrancar sin configuración excesiva.

## Maven

Maven es la herramienta de build. Se encarga de:

- descargar dependencias
- compilar
- ejecutar tests
- empaquetar en un jar

## Java 17

Java 17 es una versión LTS. Para una prueba técnica es una elección sólida porque combina estabilidad, soporte prolongado y compatibilidad amplia con Spring Boot moderno.

## API REST y endpoints

Una API REST expone recursos por HTTP. Un endpoint es una ruta concreta como `GET /likes` o `POST /likes`. En este proyecto:

- `GET /likes` consulta el total
- `POST /likes` registra un nuevo like

## OpenAPI y Swagger en este proyecto

OpenAPI es la especificación que describe formalmente la API. Swagger UI es la interfaz visual que renderiza esa especificación.

En esta implementación se dejó:

- configuración global con `@OpenAPIDefinition` en `OpenApiConfig`
- documentación de operaciones con `@Operation` y `@ApiResponse`
- documentación de DTOs con `@Schema`
- carpeta pública `src/main/resources/public` con una página simple de acceso rápido

Eso permite que alguien que está aprendiendo vea tanto la API ejecutándose como su contrato documentado.

## DDD aplicado sin sobreingeniería

DDD significa diseñar pensando en el problema del negocio. Aquí el dominio es sencillo: contar likes de forma persistente.

- Entidad principal: `LikeCounter`
- Regla principal: el contador no puede ser negativo y se incrementa mediante comportamiento propio
- Puerto de salida: cargar y guardar el contador

No se agregaron conceptos innecesarios como múltiples agregados o fábricas complejas porque para una prueba técnica importa más demostrar criterio que complejidad artificial.

## Arquitectura hexagonal aplicada

Arquitectura hexagonal significa aislar el núcleo del negocio y permitir que el exterior se conecte mediante contratos.

- Puerto de entrada: `RegisterLikeUseCase` y `GetLikeCountUseCase`
- Adaptador de entrada: `LikeController`
- Puerto de salida: `LoadLikeCounterPort` y `SaveLikeCounterPort`
- Adaptador de salida: `LikeCounterPersistenceAdapter`

La dependencia va de afuera hacia adentro. El dominio no conoce REST, ni JPA, ni Spring.

## Diagramas

Para complementar la explicación, en `Docs` quedaron dos diagramas en Mermaid:

- `diagrama-flujo-like.md`: flujo del caso de uso `Like`
- `diagrama-clases-like.md`: vista de clases y relaciones principales

Estos diagramas sirven para estudiar el recorrido de una petición y entender cómo se conectan dominio, aplicación e infraestructura.

## H2

H2 es una base de datos ligera, ideal para demos y pruebas técnicas porque no requiere instalar un servidor separado. En desarrollo se usa en memoria. En perfil `prod` se configuró en modo archivo para conservar datos entre reinicios locales sencillos.

La tabla principal es `like_counter` y guarda:

- `id`
- `count`

## Testing

Se incluyeron pruebas para:

- levantar el contexto Spring
- consultar likes por endpoint
- registrar likes por endpoint
- validar el caso de uso de aplicación

Esto cubre el núcleo de la funcionalidad y demuestra una base razonable de calidad.

## CI/CD

CI significa integrar cambios con validaciones automáticas. CD significa desplegar una versión validada.

El workflow hace:

1. checkout
2. setup de Java 17
3. build
4. tests
5. package
6. deploy a Azure

Se diseñó para no desplegar cualquier rama. Los PR a `main` solo validan. El despliegue ocurre cuando ya existe `push` a `main`, que normalmente es la consecuencia de un merge.

## Azure y GitHub Actions

GitHub Actions ejecuta el pipeline definido en YAML. Azure Web App recibe el jar empaquetado.

Secrets necesarios:

- `AZURE_WEBAPP_NAME`
- `AZURE_WEBAPP_PUBLISH_PROFILE`

### Nota sobre la advertencia de Node 20

Aunque `actions/checkout` y `actions/setup-java` ya tienen líneas compatibles con Node 24, la action `azure/webapps-deploy` todavía publica una versión que declara `node20` como runtime. Para evitar ese warning, el workflow de este proyecto despliega con el Kudu Publish API usando el `publish profile` del App Service, que además está soportado por la documentación oficial de Microsoft para artefactos JAR.

## Relación entre todas las piezas

El flujo completo es:

1. desarrollas en local
2. ejecutas tests
3. subes cambios a GitHub
4. se valida el PR
5. al mergear a `main`, se dispara el deploy
6. Azure publica la aplicación

Así se conectan Spring Boot, DDD, hexagonal, H2 y CI/CD en un ejercicio coherente y explicable.
