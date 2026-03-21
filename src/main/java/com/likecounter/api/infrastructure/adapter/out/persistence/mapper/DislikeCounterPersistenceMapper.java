package com.likecounter.api.infrastructure.adapter.out.persistence.mapper;

import com.likecounter.api.domain.model.DislikeCounter;
import com.likecounter.api.infrastructure.adapter.out.persistence.entity.DislikeCounterJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class DislikeCounterPersistenceMapper {

    public DislikeCounter toDomain(DislikeCounterJpaEntity entity) {
        return new DislikeCounter(entity.getId(), entity.getCount());
    }

    public DislikeCounterJpaEntity toEntity(DislikeCounter dislikeCounter) {
        return new DislikeCounterJpaEntity(dislikeCounter.getId(), dislikeCounter.getCount());
    }
}
