package com.likecounter.api.infrastructure.adapter.out.persistence.mapper;

import com.likecounter.api.domain.model.LikeCounter;
import com.likecounter.api.infrastructure.adapter.out.persistence.entity.LikeCounterJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class LikeCounterPersistenceMapper {

    public LikeCounter toDomain(LikeCounterJpaEntity entity) {
        return new LikeCounter(entity.getId(), entity.getCount());
    }

    public LikeCounterJpaEntity toEntity(LikeCounter likeCounter) {
        return new LikeCounterJpaEntity(likeCounter.getId(), likeCounter.getCount());
    }
}
