package com.likecounter.api.infrastructure.adapter.out.persistence;

import com.likecounter.api.domain.model.DislikeCounter;
import com.likecounter.api.domain.port.out.LoadDislikeCounterPort;
import com.likecounter.api.domain.port.out.SaveDislikeCounterPort;
import com.likecounter.api.infrastructure.adapter.out.persistence.mapper.DislikeCounterPersistenceMapper;
import com.likecounter.api.infrastructure.adapter.out.persistence.repository.SpringDataDislikeCounterRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DislikeCounterPersistenceAdapter implements LoadDislikeCounterPort, SaveDislikeCounterPort {

    private static final long DEFAULT_COUNTER_ID = 1L;

    private final SpringDataDislikeCounterRepository repository;
    private final DislikeCounterPersistenceMapper mapper;

    public DislikeCounterPersistenceAdapter(SpringDataDislikeCounterRepository repository,
                                            DislikeCounterPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<DislikeCounter> load() {
        return repository.findById(DEFAULT_COUNTER_ID)
                .map(mapper::toDomain);
    }

    @Override
    public DislikeCounter save(DislikeCounter dislikeCounter) {
        return mapper.toDomain(repository.save(mapper.toEntity(dislikeCounter)));
    }
}
