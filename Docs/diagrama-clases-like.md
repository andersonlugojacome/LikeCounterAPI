# Diagrama de clases de LikeCounterAPI

Este diagrama muestra las clases e interfaces principales del proyecto y cómo se relacionan dentro de la arquitectura hexagonal.

```mermaid
classDiagram
    class LikeCounter {
        -Long id
        -long count
        +LikeCounter(Long id, long count)
        +initializeDefault() LikeCounter
        +registerLike() void
        +getId() Long
        +getCount() long
    }

    class RegisterLikeUseCase {
        <<interface>>
        +registerLike() long
    }

    class GetLikeCountUseCase {
        <<interface>>
        +getCurrentCount() long
    }

    class LoadLikeCounterPort {
        <<interface>>
        +load() Optional~LikeCounter~
    }

    class SaveLikeCounterPort {
        <<interface>>
        +save(likeCounter) LikeCounter
    }

    class LikeCounterService {
        -LoadLikeCounterPort loadLikeCounterPort
        -SaveLikeCounterPort saveLikeCounterPort
        +registerLike() long
        +getCurrentCount() long
    }

    class LikeController {
        -RegisterLikeUseCase registerLikeUseCase
        -GetLikeCountUseCase getLikeCountUseCase
        +registerLike() LikeRegisteredResponse
        +getLikes() LikeCountResponse
    }

    class LikeCounterPersistenceAdapter {
        -SpringDataLikeCounterRepository repository
        -LikeCounterPersistenceMapper mapper
        +load() Optional~LikeCounter~
        +save(likeCounter) LikeCounter
    }

    class SpringDataLikeCounterRepository {
        <<interface>>
    }

    class LikeCounterPersistenceMapper {
        +toDomain(entity) LikeCounter
        +toEntity(domain) LikeCounterJpaEntity
    }

    class LikeCounterJpaEntity {
        -Long id
        -long count
    }

    class LikeCountResponse
    class LikeRegisteredResponse

    RegisterLikeUseCase <|.. LikeCounterService
    GetLikeCountUseCase <|.. LikeCounterService
    LoadLikeCounterPort <|.. LikeCounterPersistenceAdapter
    SaveLikeCounterPort <|.. LikeCounterPersistenceAdapter
    LikeController --> RegisterLikeUseCase
    LikeController --> GetLikeCountUseCase
    LikeCounterService --> LoadLikeCounterPort
    LikeCounterService --> SaveLikeCounterPort
    LikeCounterService --> LikeCounter
    LikeCounterPersistenceAdapter --> SpringDataLikeCounterRepository
    LikeCounterPersistenceAdapter --> LikeCounterPersistenceMapper
    LikeCounterPersistenceMapper --> LikeCounter
    LikeCounterPersistenceMapper --> LikeCounterJpaEntity
    LikeController --> LikeCountResponse
    LikeController --> LikeRegisteredResponse
```

## Lectura pedagógica

- `LikeCounter` es la entidad del dominio.
- `LikeCounterService` implementa los casos de uso y coordina la lógica.
- `LikeController` es el adaptador de entrada REST.
- `LikeCounterPersistenceAdapter` es el adaptador de salida hacia persistencia.
- `SpringDataLikeCounterRepository` y `LikeCounterJpaEntity` pertenecen a infraestructura.
- El dominio no depende de Spring, JPA ni HTTP.
