package com.likecounter.api.infrastructure.adapter.out.persistence;

import com.likecounter.api.domain.model.LikeCounter;
import com.likecounter.api.domain.port.out.LoadLikeCounterPort;
import com.likecounter.api.domain.port.out.SaveLikeCounterPort;
import com.likecounter.api.infrastructure.adapter.out.persistence.mapper.LikeCounterPersistenceMapper;
import com.likecounter.api.infrastructure.adapter.out.persistence.repository.SpringDataLikeCounterRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LikeCounterPersistenceAdapter implements LoadLikeCounterPort, SaveLikeCounterPort {

    private static final long DEFAULT_COUNTER_ID = 1L;

    private final SpringDataLikeCounterRepository repository;
    private final LikeCounterPersistenceMapper mapper;

    public LikeCounterPersistenceAdapter(SpringDataLikeCounterRepository repository,
                                         LikeCounterPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<LikeCounter> load() {
        return repository.findById(DEFAULT_COUNTER_ID)
                .map(mapper::toDomain);
    }

    @Override
    public LikeCounter save(LikeCounter likeCounter) {
        return mapper.toDomain(repository.save(mapper.toEntity(likeCounter)));
    }
}
