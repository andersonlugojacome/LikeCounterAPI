# Instalador de LikeCounterAPI

## Objetivo del laboratorio

Construir y desplegar una API REST para registrar likes usando Java 17, Spring Boot, Maven, H2, arquitectura hexagonal, DDD y GitHub Actions con despliegue a Azure.

## Prerrequisitos

- JDK 17 instalado
- Maven 3.9+ instalado
- Git instalado
- Cuenta en GitHub
- Cuenta en Azure
- Editor como IntelliJ IDEA o VS Code

## Instalación de herramientas

### Verificar Java

```bash
java -version
```

### Verificar Maven

```bash
mvn -version
```

### Verificar Git

```bash
git --version
```

## Creación paso a paso del proyecto

1. Crear un repositorio llamado `LikeCounterAPI`.
2. Crear el archivo `pom.xml` con Spring Boot, Web, JPA, H2, Actuator y Test.
3. Crear la clase principal `LikeCounterApiApplication`.
4. Separar paquetes en `domain`, `application` e `infrastructure`.
5. Modelar el agregado `LikeCounter` dentro del dominio.
6. Definir puertos de entrada para consultar e incrementar likes.
7. Definir puertos de salida para cargar y guardar el contador.
8. Implementar el servicio de aplicación que usa los puertos.
9. Crear el controlador REST.
10. Crear el adaptador JPA con H2.
11. Configurar `application.yml`.
12. Agregar pruebas.
13. Crear workflow de GitHub Actions.
14. Configurar despliegue a Azure Web App.

## Estructura del proyecto

```text
src
├── main
│   ├── java/com/likecounter/api
│   │   ├── domain
│   │   ├── application
│   │   └── infrastructure
│   └── resources
├── test
│   └── java/com/likecounter/api
Docs
.github/workflows
README.md
instalador.md
examen.md
VERIFICACION_FINAL.md
pom.xml
```

## Arquitectura hexagonal elegida

- El dominio contiene la entidad `LikeCounter`.
- La aplicación contiene el servicio `LikeCounterService`.
- El puerto de entrada lo consumen los controladores REST.
- El puerto de salida lo implementa el adaptador JPA.
- Spring Boot ensambla todo con inyección de dependencias.

## Enfoque DDD usado

- El dominio del problema es contar likes.
- La entidad principal es `LikeCounter`.
- La regla de negocio básica es que el contador no puede ser negativo y solo se incrementa mediante el comportamiento del dominio.
- La persistencia está separada de la entidad de dominio para evitar acoplarla con JPA.

## Configuración de H2

La aplicación usa H2 en memoria para desarrollo y pruebas rápidas.

- URL local: `jdbc:h2:mem:likecounterdb`
- Consola: `http://localhost:8080/h2-console`
- Usuario: `sa`
- Password: vacío

## Cómo levantar localmente

```bash
mvn spring-boot:run
```

## Cómo probar el endpoint de Like

### Consultar el total

```bash
curl http://localhost:8080/likes
```

### Registrar un like

```bash
curl -X POST http://localhost:8080/likes
```

## Cómo abrir Swagger y OpenAPI

Después de levantar la aplicación puedes abrir:

- `http://localhost:8080/` para ver la página pública de acceso rápido.
- `http://localhost:8080/swagger-ui.html` para usar Swagger UI.
- `http://localhost:8080/api-docs` para ver el JSON OpenAPI.

La documentación se genera automáticamente porque el proyecto usa:

- `springdoc-openapi`
- anotaciones `@OpenAPIDefinition` para metadatos globales
- anotaciones `@Operation`, `@ApiResponse` y `@Schema` en controladores y DTOs

## Cómo correr tests

```bash
mvn test
```

## Cómo crear el repo en GitHub

1. Crear el repositorio vacío.
2. Inicializar Git localmente.
3. Asociar el remoto.
4. Subir el código.

```bash
git init
git remote add origin <URL_DEL_REPO>
git checkout -b main
git add .
git commit -m "Initial implementation"
git push -u origin main
```

## Cómo crear el recurso en Azure

1. Entrar al portal de Azure.
2. Crear un recurso `Web App`.
3. Elegir runtime `Java 17`.
4. Elegir plan básico o free según disponibilidad.
5. Guardar el nombre del recurso.

## Cómo configurar secrets en GitHub

Ir a `Settings > Secrets and variables > Actions` y crear:

- `AZURE_WEBAPP_NAME`: nombre exacto de la Web App.
- `AZURE_WEBAPP_PUBLISH_PROFILE`: contenido completo del publish profile descargado desde Azure.

## Cómo crear y entender el pipeline

El archivo `.github/workflows/ci-cd.yml` hace dos cosas:

1. Valida en PR a `main`.
2. Despliega solo cuando hay `push` a `main`.

Eso permite probar cambios antes del merge y desplegar solo código integrado.

## Cómo mergear a main

1. Crear una rama feature.
2. Hacer cambios y push.
3. Abrir Pull Request hacia `main`.
4. Esperar que el workflow pase.
5. Hacer merge.
6. El `push` resultante a `main` dispara el deploy.

## Cómo verificar el despliegue

1. Entrar a GitHub Actions y revisar el job `deploy-to-azure`.
2. Abrir la URL pública de Azure Web App.
3. Probar:

```bash
curl https://<tu-app>.azurewebsites.net/likes
curl -X POST https://<tu-app>.azurewebsites.net/likes
curl https://<tu-app>.azurewebsites.net/actuator/health
```

## Errores comunes y solución

- `java: command not found`: instalar JDK 17 y configurar `JAVA_HOME`.
- `mvn: command not found`: instalar Maven.
- `Port 8080 already in use`: cambiar el puerto o detener el proceso ocupado.
- `H2 Console disabled`: revisar `application.yml`.
- `Deploy failed`: revisar `AZURE_WEBAPP_NAME` y `AZURE_WEBAPP_PUBLISH_PROFILE`.
- `Workflow no despliega`: confirmar que el evento fue `push` a `main`.
