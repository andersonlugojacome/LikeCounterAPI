
Quiero que actúes como un arquitecto senior, backend engineer, DevOps engineer y mentor pedagógico especializado en Java 17, Maven, Spring Boot, arquitectura hexagonal, DDD, CI/CD y despliegues en Azure.

Tu objetivo es crear un proyecto completo, funcional, entendible y didáctico.

## CONTEXTO
Necesito una prueba técnica / proyecto de entrenamiento con estas características:

- La documnetación debe estar en /Docs
- Lenguaje: Java 17
- Build tool: Maven
- Framework: Spring Boot
- Arquitectura: Hexagonal Architecture (Ports and Adapters)
- Enfoque de diseño: DDD (Domain-Driven Design)
- Base de datos: H2
- API REST
- Debe incluir una API básica y un caso funcional real
- Debe incluir pipeline CI/CD
- El pipeline debe ejecutarse para desplegar **únicamente cuando haya merge a la rama `main`**
- El despliegue debe ser en Azure, preferiblemente en Azure Web App o el servicio más simple y apropiado para este caso
- Debe incluir pruebas automáticas
- Debe explicarme de forma pedagógica cada paso
- Debe dejarme un manual práctico paso a paso en un archivo `instalador.md`
- Debe dejarme una evaluación práctica/teórica en un archivo `examen.md`

## CASO FUNCIONAL OBLIGATORIO
Además del típico ejemplo básico, quiero un caso de uso real y sencillo:

### Caso de uso: Botón Like
Debes implementar la lógica para un botón que diga `Like`.

Comportamiento esperado:
- cada vez que se haga clic sobre el botón Like, se debe registrar el evento
- debe almacenarse en base de datos H2 la cantidad acumulada de clics
- el sistema debe permitir consultar cuántos likes se han dado
- la API debe exponer endpoints claros para:
  - registrar un clic / like
  - consultar el total de likes
- opcionalmente, puedes incluir un endpoint de health o uno básico de saludo si lo consideras útil

Ejemplo esperado:
- `POST /likes` incrementa el contador
- `GET /likes` devuelve el total actual

Ejemplo de respuesta del GET:
```json
{
  "count": 5
}

Ejemplo de respuesta del POST:

{
  "message": "Like registrado correctamente",
  "count": 6
}

Quiero que la lógica de negocio esté bien separada y que este caso de uso sirva para demostrar:
	•	entidad de dominio
	•	caso de uso / aplicación
	•	puerto de salida
	•	adaptador de persistencia
	•	adaptador de entrada REST

ARQUITECTURA OBLIGATORIA

Debes implementarlo usando arquitectura hexagonal y DDD de forma simple pero correcta.

Requisitos de arquitectura

Quiero que expliques e implementes claramente estas capas o responsabilidades:

Dominio
	•	entidades de dominio
	•	value objects si aplica
	•	reglas de negocio
	•	puertos de dominio o contratos necesarios

Aplicación
	•	casos de uso
	•	servicios de aplicación
	•	orquestación de reglas de negocio

Infraestructura
	•	persistencia con H2
	•	implementación de repositorios/puertos
	•	configuración de Spring
	•	adaptadores

Entrada
	•	controlador REST
	•	DTOs request/response si aplica

Salida
	•	repositorio de persistencia como adaptador secundario

FORMA DE TRABAJAR

Quiero que trabajes como si yo estuviera aprendiendo. No solo implementes, sino que enseñes.

Cada vez que tomes una decisión técnica:
	1.	Explica qué vas a hacer
	2.	Explica por qué lo haces así
	3.	Explica qué otras alternativas existen
	4.	Explica por qué esta solución es adecuada para una prueba técnica o proyecto base
	5.	Usa lenguaje claro, pedagógico y estructurado

ENTREGABLES OBLIGATORIOS

Debes generar TODO lo siguiente:

1. Proyecto funcional

Crea un proyecto Spring Boot con Maven y Java 17 que incluya:
	•	estructura limpia y profesional
	•	arquitectura hexagonal
	•	enfoque DDD
	•	endpoint básico de prueba si lo ves conveniente
	•	endpoints del caso de uso Like
	•	persistencia en H2
	•	inicialización correcta del proyecto

2. Buenas prácticas mínimas

Incluye como mínimo:
	•	separación clara por paquetes según arquitectura
	•	código limpio y fácil de explicar
	•	controller claro
	•	DTOs cuando corresponda
	•	application.yml o application.properties
	•	configuración H2
	•	.gitignore
	•	README.md

3. Persistencia H2

Configura H2 correctamente y explícame:
	•	qué es H2
	•	por qué sirve para pruebas técnicas
	•	cómo se configura
	•	cómo ver la consola H2
	•	cómo se almacena el contador de likes
	•	qué tabla o estructura se crea
	•	cómo consultar los datos manualmente si quiero revisar

4. DDD explicado y aplicado

Quiero que me expliques pedagógicamente:
	•	qué es DDD
	•	qué significa dominio en este proyecto
	•	cuál es la entidad principal
	•	por qué el contador de likes pertenece al dominio
	•	qué diferencia hay entre dominio, aplicación e infraestructura
	•	cómo se aterriza DDD sin complicar demasiado una prueba técnica

5. Arquitectura hexagonal explicada y aplicada

Quiero que me expliques pedagógicamente:
	•	qué es arquitectura hexagonal
	•	qué son puertos y adaptadores
	•	cuál es el puerto de entrada
	•	cuál es el puerto de salida
	•	cómo Spring Boot ayuda a implementarla
	•	qué parte del proyecto depende de qué parte
	•	cómo evitar acoplar la lógica de negocio a la base de datos o al framework

6. Testing automático

Debes incluir pruebas automáticas:
	•	al menos un test del contexto (contextLoads)
	•	al menos un test del endpoint de consulta de likes
	•	al menos un test del endpoint que registra el like
	•	al menos un test del caso de uso o servicio de aplicación
	•	idealmente usando JUnit 5 + MockMvc
	•	si aplica, tests unitarios del dominio
	•	explica qué valida cada test
	•	asegúrate de que el proyecto compile y los tests pasen

7. CI/CD

Configura pipeline de CI/CD con GitHub Actions.

Requisitos obligatorios:
	•	el flujo de despliegue solo debe ejecutarse con cambios en main
	•	idealmente disparado con push a main posterior al merge
	•	si decides usar otra condición adicional, explícalo
	•	el pipeline debe:
	1.	hacer checkout
	2.	configurar Java 17
	3.	compilar con Maven
	4.	correr tests
	5.	empaquetar la app
	6.	desplegar en Azure

8. Azure deployment

Configura el proyecto para despliegue en Azure con la opción más simple y profesional para una demo:
	•	preferiblemente Azure Web App
	•	explica los prerequisitos:
	•	cuenta Azure
	•	recurso creado
	•	publish profile o secrets necesarios
	•	define exactamente qué secrets hay que configurar en GitHub
	•	explica paso a paso cómo conectar GitHub Actions con Azure
	•	explica cómo verificar que el despliegue funcionó

9. Explicación pedagógica

Quiero un informe explicativo donde me enseñes:
	•	qué es Spring Boot
	•	qué hace Maven
	•	qué significa Java 17 en este contexto
	•	qué es una API REST
	•	qué es un endpoint
	•	qué es CI
	•	qué es CD
	•	qué significa pipeline
	•	qué hace GitHub Actions
	•	por qué el despliegue ocurre solo al mergear a main
	•	qué son los secrets de GitHub
	•	cómo se relaciona todo el flujo desde código local hasta nube
	•	cómo se relacionan hexagonal architecture + DDD + Spring Boot + H2 + CI/CD

Explícalo de forma práctica, no académica solamente.

10. Archivo instalador.md

Debes crear un archivo instalador.md con enfoque de entrenamiento práctico.

Ese archivo debe incluir:
	•	objetivo del proyecto
	•	prerequisitos
	•	instalación de herramientas necesarias
	•	creación paso a paso del proyecto desde cero
	•	explicación de la estructura del proyecto
	•	explicación de la arquitectura hexagonal elegida
	•	explicación del enfoque DDD usado
	•	configuración de H2
	•	cómo levantar localmente
	•	cómo probar el endpoint de Like
	•	cómo correr tests
	•	cómo crear el repo en GitHub
	•	cómo subir el código
	•	cómo crear el recurso en Azure
	•	cómo configurar secrets en GitHub
	•	cómo crear/entender el pipeline
	•	cómo mergear a main
	•	cómo verificar el despliegue
	•	errores comunes y cómo solucionarlos

Quiero que el instalador.md esté escrito como si fuera una guía de laboratorio para aprender haciéndolo yo mismo.

11. Archivo examen.md

Debes crear un archivo examen.md para evaluarme.

Debe incluir:
	•	15 preguntas teóricas
	•	10 preguntas prácticas
	•	1 ejercicio final integrador
	•	respuestas sugeridas o solucionario en una sección aparte
	•	preguntas sobre:
	•	Java 17
	•	Maven
	•	Spring Boot
	•	REST
	•	testing
	•	GitHub Actions
	•	CI/CD
	•	Azure deploy
	•	rama main
	•	merge y triggers del pipeline
	•	arquitectura hexagonal
	•	DDD
	•	H2
	•	puertos y adaptadores
	•	caso de uso Like

El examen debe ayudarme a comprobar que realmente entendí el proyecto.

ESTRUCTURA ESPERADA DEL REPOSITORIO

Propón y crea una estructura similar a esta si aplica:
	•	src/main/java/.../domain/...
	•	src/main/java/.../application/...
	•	src/main/java/.../infrastructure/...
	•	src/main/java/.../entrypoints/... o .../inbound/...
	•	src/main/java/.../outbound/...
	•	src/main/resources/...
	•	src/test/java/...
	•	.github/workflows/...
	•	README.md
	•	instalador.md
	•	examen.md
	•	pom.xml

DETALLE IMPORTANTE SOBRE EL DOMINIO

No quiero una simple variable global incrementándose sin diseño.
Quiero que realmente lo modeles bien.

Por ejemplo:
	•	una entidad o agregado que represente el contador de likes
	•	reglas claras para incrementar
	•	repositorio como puerto
	•	implementación JPA/H2 en infraestructura
	•	separación entre modelo de dominio y entidad de persistencia si lo consideras adecuado
	•	explica cuándo conviene separarlas y cuándo no

DETALLE IMPORTANTE SOBRE EL PIPELINE

Quiero que expliques con mucha claridad esto:
	•	por qué el pipeline no debe desplegar en cualquier rama
	•	por qué sí debe desplegar al integrar a main
	•	diferencia entre ejecutar tests en feature branches y desplegar solo desde main
	•	cómo se vería el trigger en GitHub Actions
	•	cómo proteger la rama main como buena práctica, aunque sea opcional

SOBRE LOS ARCHIVOS YAML / WORKFLOWS

Quiero que generes el workflow completo y bien comentado si es posible.
Quiero que expliques línea por línea qué hace el YAML del pipeline.

SOBRE EL README

Además de lo técnico, el README.md debe incluir:
	•	descripción del proyecto
	•	stack tecnológico
	•	explicación de la arquitectura
	•	explicación del caso de uso Like
	•	cómo ejecutar localmente
	•	cómo correr tests
	•	cómo funciona CI/CD
	•	cómo desplegar
	•	endpoints disponibles
	•	ejemplo de request/response

VALIDACIÓN FINAL

Después de generar todo:
	1.	revisa coherencia de nombres de paquetes, clases y rutas
	2.	valida que el proyecto compile
	3.	valida que los tests tengan sentido
	4.	valida que el workflow esté alineado con la estructura real del proyecto
	5.	valida que la documentación coincida con lo implementado
	6.	haz una sección final llamada VERIFICACION_FINAL.md o inclúyela en el informe, donde confirmes:
	•	qué se implementó
	•	qué falta si algo no pudo automatizarse
	•	qué pasos manuales debo hacer yo en Azure/GitHub
	•	cómo comprobar el éxito del ejercicio

ESTILO DE RESPUESTA

Quiero que me entregues la solución de manera ordenada en este formato:
	1.	Resumen de la solución
	2.	Explicación conceptual pedagógica
	3.	Explicación de la arquitectura hexagonal y DDD aplicadas
	4.	Estructura del proyecto
	5.	Código completo archivo por archivo
	6.	Persistencia H2
	7.	Tests
	8.	Workflow GitHub Actions
	9.	Pasos de Azure
	10.	Contenido de README.md
	11.	Contenido de instalador.md
	12.	Contenido de examen.md
	13.	Validación final
	14.	Siguientes pasos recomendados para evolucionar el proyecto

RESTRICCIONES IMPORTANTES
	•	No hagas una solución excesivamente compleja
	•	Debe ser entendible para alguien que está aprendiendo
	•	Pero sí debe verse profesional
	•	Usa convenciones reales de industria
	•	No omitas archivos importantes
	•	No asumas que ya sé DevOps
	•	Explícame como mentor, no solo como generador de código
	•	No hagas una pseudo-arquitectura; quiero que realmente se note la separación hexagonal y el enfoque DDD

BONUS SI PUEDES

Si lo consideras adecuado, agrega:
	•	perfil dev y prod
	•	Actuator básico
	•	health endpoint
	•	OpenAPI/Swagger
	•	recomendaciones para entrevista técnica basadas en este proyecto
	•	explicación de cómo evolucionar el contador de likes a una funcionalidad real de engagement

Ahora genera la solución completa.

Y este sería el **mini prompt de revisión** para pegar después:

```text
Antes de terminar, revisa toda la solución como si fueras revisor técnico senior.
Verifica:
- consistencia entre DDD y arquitectura hexagonal
- que el dominio no dependa de Spring ni JPA
- que el caso de uso Like esté bien modelado
- que H2 esté correctamente configurada
- que los tests correspondan a los endpoints y casos de uso reales
- que el workflow tenga los paths correctos
- que el deploy a Azure esté bien planteado
- que `instalador.md` sea realmente ejecutable por un principiante
- que `examen.md` evalúe lo que enseñaste
- que todo esté escrito de forma pedagógica
Luego corrige cualquier inconsistencia y entrega la versión final depurada.


El nombre del proyecto es LikeCounterAPI, y te permite hablar de dominio, persistencia y arquitectura.