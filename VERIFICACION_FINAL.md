# Verificación final

## Qué se implementó

- Proyecto Maven con Java 17 y Spring Boot.
- Arquitectura hexagonal con paquetes `domain`, `application` e `infrastructure`.
- Caso de uso `Like` con endpoints `POST /likes` y `GET /likes`.
- Persistencia H2 mediante JPA.
- Health básico y Actuator.
- OpenAPI/Swagger.
- Tests automáticos.
- Workflow CI/CD con despliegue condicionado a `main`.
- Documentación pedagógica en `README.md`, `instalador.md`, `examen.md` y `Docs`.

## Qué falta automatizar

- La creación del recurso Azure Web App no se automatiza desde este repositorio.
- La carga de secrets en GitHub se hace manualmente.

## Pasos manuales en Azure y GitHub

1. Crear la Web App en Azure.
2. Descargar el publish profile.
3. Crear los secrets en GitHub.
4. Hacer merge a `main`.
5. Verificar el despliegue en GitHub Actions y en la URL publicada.

## Cómo comprobar el éxito

- `mvn test` debe pasar.
- `GET /likes` debe responder correctamente.
- `POST /likes` debe incrementar el contador.
- La consola H2 debe mostrar la tabla `LIKE_COUNTER`.
- El workflow debe ejecutar deploy solo tras `push` a `main`.
