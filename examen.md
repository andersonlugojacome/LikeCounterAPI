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

## Respuestas sugeridas para leer en voz alta

### Guion oral de respuestas teóricas

1. "Java 17 es una buena elección porque es una versión LTS, es decir, de soporte prolongado. Eso la hace estable para proyectos backend y además es totalmente compatible con versiones modernas de Spring Boot."
2. "Maven en este proyecto se usa para administrar dependencias, compilar el código, ejecutar pruebas y generar el artefacto final que luego se despliega."
3. "Spring Boot resuelve la complejidad de configuración inicial. Nos permite arrancar rápido una API REST con servidor embebido, inyección de dependencias y convenciones listas para usar."
4. "Una API REST es una forma de exponer funcionalidades del sistema usando HTTP y recursos bien definidos. En este proyecto, el recurso principal es el contador de likes."
5. "Un endpoint es una ruta concreta de la API. Por ejemplo, `GET /likes` consulta el total y `POST /likes` registra un nuevo like."
6. "Que H2 sea embebida significa que no necesito instalar un motor externo para empezar a trabajar. Es ideal para pruebas técnicas porque simplifica mucho el entorno."
7. "DDD significa Domain-Driven Design. En este proyecto el dominio es la gestión del contador de likes, es decir, la lógica de negocio asociada a registrar y consultar likes."
8. "Una entidad de dominio es un objeto que tiene identidad y comportamiento. Aquí la entidad principal es `LikeCounter`, porque representa el contador y define cómo se incrementa."
9. "La arquitectura hexagonal busca aislar la lógica de negocio del resto del mundo. Para eso usa puertos y adaptadores, de manera que el dominio no dependa ni de la base de datos ni del framework web."
10. "El puerto de entrada representa lo que el sistema ofrece, por ejemplo registrar un like. El puerto de salida representa lo que el sistema necesita de afuera, por ejemplo guardar o leer el contador desde la base de datos."
11. "Conviene que el dominio no dependa de Spring ni de JPA para mantenerlo limpio, testeable y fácil de evolucionar. Así la lógica de negocio no queda amarrada a una tecnología específica."
12. "Un test con MockMvc valida el comportamiento HTTP real del endpoint. Comprueba rutas, códigos de estado y respuestas JSON sin necesidad de levantar manualmente un servidor externo."
13. "CI significa integración continua. Es el proceso de compilar y probar automáticamente cada cambio para detectar errores lo antes posible."
14. "CD significa entrega o despliegue continuo. En este caso, después de validar el build y los tests, la aplicación puede desplegarse a Azure."
15. "El despliegue debe ocurrir solo al integrar a `main` porque esa rama representa la versión estable e integrada del sistema. Así evitamos desplegar trabajo incompleto desde ramas feature."

### Guion oral de respuestas prácticas

1. "Si ejecuto `GET /likes` al inicio, espero recibir un JSON con `count` en cero, porque todavía no se ha registrado ningún like."
2. "Si hago tres llamadas a `POST /likes`, el contador debe incrementarse en uno cada vez. Al final, al consultar `GET /likes`, el total debería ser tres."
3. "En la consola H2 debo buscar la tabla `LIKE_COUNTER`, que es donde se persiste el identificador del contador y el valor acumulado."
4. "La clase que actúa como puerto de salida implementado hacia persistencia es `LikeCounterPersistenceAdapter`, porque conecta el caso de uso con JPA y H2."
5. "La clase que implementa el caso de uso es `LikeCounterService`, porque orquesta cargar el contador, incrementarlo y volverlo a guardar."
6. "Para cambiar el mensaje de `POST /likes`, puedo modificar la respuesta construida en el controlador o ajustar el DTO de respuesta si quiero cambiar también el contrato."
7. "El endpoint `GET /hello` ya existe en `HealthController` y sirve como verificación básica de que la API está viva."
8. "Los tests se ejecutan con Maven usando `mvn test`, y eso valida tanto el contexto Spring como el caso de uso y los endpoints."
9. "La condición que evita desplegar desde una rama feature está en el workflow y exige que el evento sea `push` sobre `refs/heads/main`."
10. "Los secrets necesarios para Azure son el nombre de la Web App y el publish profile. En este proyecto se usan `AZURE_WEBAPP_NAME` y `AZURE_WEBAPP_PUBLISH_PROFILE`."

### Guion oral del ejercicio integrador

"Si quisiera agregar dislikes, lo primero sería extender el dominio para modelar esa nueva regla de negocio sin mezclarla con infraestructura. Luego ampliaría la API con endpoints claros para registrar y consultar dislikes. Después ajustaría persistencia para guardar ambos contadores, actualizaría los tests para cubrir el nuevo comportamiento y revisaría el pipeline para asegurar que build, pruebas y despliegue sigan funcionando sin cambios manuales inesperados."
