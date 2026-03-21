package com.likecounter.api.infrastructure.adapter.out.persistence.repository;

import com.likecounter.api.infrastructure.adapter.out.persistence.entity.DislikeCounterJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataDislikeCounterRepository extends JpaRepository<DislikeCounterJpaEntity, Long> {
}
