# Examen de LikeCounterAPI

## Instrucciones

Responde primero sin mirar el solucionario. Después compara tus respuestas.

## 15 preguntas teóricas

1. ¿Qué ventaja ofrece Java 17 en un proyecto backend moderno?
2. ¿Para qué sirve Maven en este proyecto?
3. ¿Qué problema resuelve Spring Boot?
4. ¿Qué es una API REST?
5. ¿Qué es un endpoint?
6. ¿Qué significa que H2 sea una base de datos embebida?
7. ¿Qué es DDD y cuál es el dominio de este proyecto?
8. ¿Qué es una entidad de dominio?
9. ¿Qué es arquitectura hexagonal?
10. ¿Qué diferencia hay entre puerto de entrada y puerto de salida?
11. ¿Por qué conviene que el dominio no dependa de Spring ni de JPA?
12. ¿Qué valida un test de integración con MockMvc?
13. ¿Qué significa CI?
14. ¿Qué significa CD?
15. ¿Por qué el despliegue debe ocurrir solo al integrar a `main`?

## 10 preguntas prácticas

1. Ejecuta la aplicación y consulta el endpoint `GET /likes`.
2. Registra tres likes consecutivos y muestra el resultado final.
3. Entra a la consola H2 y localiza la tabla que guarda el contador.
4. Explica qué clase actúa como puerto de salida.
5. Explica qué clase implementa el caso de uso de registrar like.
6. Modifica el mensaje de respuesta del `POST /likes`.
7. Agrega un endpoint de lectura simple para `GET /hello`.
8. Ejecuta los tests del proyecto.
9. Identifica en el workflow la condición que evita desplegar desde una rama feature.
10. Describe qué secrets son necesarios para Azure.

## Ejercicio final integrador

Extiende el proyecto para soportar dislikes sin romper la separación entre dominio, aplicación e infraestructura. Debes proponer:

- cambios de dominio
- cambios de API
- cambios de persistencia
- cambios de tests
- cambios de pipeline si fueran necesarios

## Solucionario sugerido

### Respuestas teóricas sugeridas

1. Java 17 aporta soporte LTS, mejor rendimiento y sintaxis moderna estable.
2. Maven gestiona dependencias, build, tests y empaquetado.
3. Spring Boot acelera la configuración y el arranque del proyecto.
4. REST es un estilo para exponer recursos mediante HTTP.
5. Un endpoint es una ruta HTTP concreta de la API.
6. Significa que puede correr dentro del mismo proceso o con configuración mínima.
7. DDD es diseño guiado por el dominio; aquí el dominio es la gestión del contador de likes.
8. Es un objeto con identidad y comportamiento relevante para el negocio.
9. Es una arquitectura basada en puertos y adaptadores para aislar la lógica de negocio.
10. El puerto de entrada recibe solicitudes al sistema; el de salida comunica el dominio con recursos externos.
11. Para evitar acoplamiento y facilitar pruebas y evolución.
12. Valida comportamiento HTTP real del controlador y la integración de capas.
13. CI es integración continua: compilar y probar automáticamente.
14. CD es entrega o despliegue continuo hacia un entorno.
15. Porque `main` representa el código integrado y estable.

### Respuestas prácticas sugeridas

1. Debe devolver `{"count":0}` si aún no hay likes.
2. El contador final debe reflejar tres incrementos.
3. La tabla es `LIKE_COUNTER`.
4. `LikeCounterPersistenceAdapter`.
5. `LikeCounterService`.
6. Cambiando `LikeRegisteredResponse` o el controlador.
7. Ya existe en `HealthController`.
8. Con `mvn test`.
9. La condición `if: github.event_name == 'push' && github.ref == 'refs/heads/main'`.
10. `AZURE_WEBAPP_NAME` y `AZURE_WEBAPP_PUBLISH_PROFILE`.
