package com.likecounter.api.infrastructure.adapter.out.persistence.repository;

import com.likecounter.api.infrastructure.adapter.out.persistence.entity.LikeCounterJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataLikeCounterRepository extends JpaRepository<LikeCounterJpaEntity, Long> {
}
